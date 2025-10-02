package controller;

import dao.MaterialDAO;
import dao.MaterialInMemoryDAO;
import model.Item;
import view.MaterialUI;

import java.util.List;

public class MaterialController {
    private MaterialDAO materialDAO;
    private MaterialUI materialUI;

    public MaterialController() {
        this.materialDAO = new MaterialInMemoryDAO();
        this.materialUI = new MaterialUI();
    }

    public void registrarMaterial() {
        try {
            int tipoOpcion = materialUI.capturarOpcionTipo();
            Item material = null;

            switch (tipoOpcion) {
                case 1:
                    material = materialUI.capturarDatosLibro();
                    break;
                case 2:
                    material = materialUI.capturarDatosRevista();
                    break;
                default:
                    materialUI.mostrarError("Opción inválida");
                    return;
            }

            if (!validarDatos(material)) {
                return;
            }

            if (materialDAO.existeTitulo(material.getTitle())) {
                materialUI.mostrarError("Ya existe un material con ese título");
                return;
            }

            Long id = materialDAO.insertar(material);
            materialUI.mostrarExito("Material registrado exitosamente con ID: " + id);

        } catch (Exception e) {
            materialUI.mostrarError("Error al registrar material: " + e.getMessage());
        }
    }

    public void buscarMaterialPorId() {
        try {
            Long id = materialUI.capturarId();
            Item material = materialDAO.buscarPorId(id);
            materialUI.mostrarMaterial(material);
        } catch (Exception e) {
            materialUI.mostrarError("Error al buscar material: " + e.getMessage());
        }
    }

    public void buscarMaterialesPorTitulo() {
        try {
            String titulo = materialUI.capturarTexto("Ingrese parte del título a buscar");
            List<Item> materiales = materialDAO.buscarPorTitulo(titulo);

            if (materiales.isEmpty()) {
                materialUI.mostrarMensaje("No se encontraron materiales con ese título");
            } else {
                materialUI.mostrarListaMateriales(materiales);
            }
        } catch (Exception e) {
            materialUI.mostrarError("Error al buscar materiales: " + e.getMessage());
        }
    }

    public void buscarMaterialesPorAutor() {
        try {
            String autor = materialUI.capturarTexto("Ingrese parte del nombre del autor");
            List<Item> materiales = materialDAO.buscarPorAutor(autor);

            if (materiales.isEmpty()) {
                materialUI.mostrarMensaje("No se encontraron materiales de ese autor");
            } else {
                materialUI.mostrarListaMateriales(materiales);
            }
        } catch (Exception e) {
            materialUI.mostrarError("Error al buscar materiales: " + e.getMessage());
        }
    }

    public void buscarMaterialesPorTipo() {
        try {
            System.out.println("Buscar por tipo:");
            System.out.println("1. Libros");
            System.out.println("2. Revistas");
            System.out.print("Seleccione (1-2): ");

            int opcion = materialUI.capturarOpcionTipo();
            String tipo = (opcion == 1) ? "BOOK" : "MAGAZINE";

            List<Item> materiales = materialDAO.buscarPorTipo(tipo);

            if (materiales.isEmpty()) {
                materialUI.mostrarMensaje("No se encontraron materiales de ese tipo");
            } else {
                materialUI.mostrarListaMateriales(materiales);
            }
        } catch (Exception e) {
            materialUI.mostrarError("Error al buscar materiales: " + e.getMessage());
        }
    }

    public void listarTodosLosMateriales() {
        try {
            List<Item> materiales = materialDAO.listarTodos();
            materialUI.mostrarListaMateriales(materiales);
        } catch (Exception e) {
            materialUI.mostrarError("Error al listar materiales: " + e.getMessage());
        }
    }

    public void listarMaterialesDisponibles() {
        try {
            List<Item> materiales = materialDAO.listarDisponibles();
            materialUI.mostrarListaMateriales(materiales);
        } catch (Exception e) {
            materialUI.mostrarError("Error al listar materiales disponibles: " + e.getMessage());
        }
    }

    public void actualizarMaterial() {
        try {
            Long id = materialUI.capturarId();
            Item materialExistente = materialDAO.buscarPorId(id);

            if (materialExistente == null) {
                materialUI.mostrarError("Material no encontrado");
                return;
            }

            materialUI.mostrarMensaje("Material actual:");
            materialUI.mostrarMaterial(materialExistente);

            materialUI.mostrarMensaje("\nIngrese los nuevos datos:");

            Item materialActualizado;
            if (materialExistente.getType().equals("BOOK")) {
                materialActualizado = materialUI.capturarDatosLibro();
            } else {
                materialActualizado = materialUI.capturarDatosRevista();
            }

            materialActualizado.setMaterialId(id);
            materialActualizado.setCreatedAt(materialExistente.getCreatedAt());

            if (!validarDatos(materialActualizado)) {
                return;
            }

            Item materialConMismoTitulo = materialDAO.buscarPorTitulo(materialActualizado.getTitle())
                    .stream()
                    .filter(m -> !m.getMaterialId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (materialConMismoTitulo != null) {
                materialUI.mostrarError("Ya existe otro material con ese título");
                return;
            }

            boolean actualizado = materialDAO.actualizar(materialActualizado);
            if (actualizado) {
                materialUI.mostrarExito("Material actualizado exitosamente");
            } else {
                materialUI.mostrarError("No se pudo actualizar el material");
            }

        } catch (Exception e) {
            materialUI.mostrarError("Error al actualizar material: " + e.getMessage());
        }
    }

    public void eliminarMaterial() {
        try {
            Long id = materialUI.capturarId();
            Item material = materialDAO.buscarPorId(id);

            if (material == null) {
                materialUI.mostrarError("Material no encontrado");
                return;
            }

            materialUI.mostrarMensaje("Material a eliminar:");
            materialUI.mostrarMaterial(material);

            if (materialUI.confirmarAccion("¿Está seguro de eliminar este material?")) {
                boolean eliminado = materialDAO.eliminar(id);
                if (eliminado) {
                    materialUI.mostrarExito("Material eliminado exitosamente");
                } else {
                    materialUI.mostrarError("No se pudo eliminar el material");
                }
            } else {
                materialUI.mostrarMensaje("Operación cancelada");
            }

        } catch (Exception e) {
            materialUI.mostrarError("Error al eliminar material: " + e.getMessage());
        }
    }

    private boolean validarDatos(Item material) {
        if (!material.isValidTitle()) {
            materialUI.mostrarError("Título inválido");
            return false;
        }

        if (!material.isValidAuthor()) {
            materialUI.mostrarError("Autor inválido");
            return false;
        }

        if (!material.isValidPublisher()) {
            materialUI.mostrarError("Editorial inválida");
            return false;
        }

        if (!material.isValidYear()) {
            materialUI.mostrarError("Año de publicación inválido");
            return false;
        }

        return true;
    }
}