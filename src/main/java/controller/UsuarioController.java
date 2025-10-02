package controller;

import dao.UsuarioDAO;
import dao.UsuarioInMemoryDAO;
import model.Usuario;
import view.UsuarioUI;

import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    private UsuarioUI usuarioUI;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioInMemoryDAO();
        this.usuarioUI = new UsuarioUI();
    }


    public void registrarUsuario() {
        try {
            Usuario usuario = usuarioUI.capturarDatosUsuario();

            if (!validarDatos(usuario)) {
                return;
            }

            if (usuarioDAO.existeDocumentId(usuario.getDocumentId())) {
                usuarioUI.mostrarError("User with that document ID already exists");
                return;
            }

            if (usuario.getEmail() != null && usuarioDAO.existeEmail(usuario.getEmail())) {
                usuarioUI.mostrarError("User with that email already exists");
                return;
            }

            Long id = usuarioDAO.insertar(usuario);
            usuarioUI.mostrarExito("User registered successfully with ID: " + id);

            // DEBUG: Verificar que se guardó
            System.out.println("DEBUG: Total usuarios en memoria: " + usuarioDAO.listarTodos().size());

        } catch (Exception e) {
            usuarioUI.mostrarError("Error registering user: " + e.getMessage());
        }
    }

    public void buscarUsuarioPorId() {
        try {
            Long id = usuarioUI.capturarId();
            Usuario usuario = usuarioDAO.buscarPorId(id);
            usuarioUI.mostrarUsuario(usuario);
        } catch (Exception e) {
            usuarioUI.mostrarError("Error al buscar usuario: " + e.getMessage());
        }
    }

    public void buscarUsuarioPorDocumento() {
        try {
            String documento = usuarioUI.capturarDocumento();
            Usuario usuario = usuarioDAO.buscarPorDocumentId(documento);
            usuarioUI.mostrarUsuario(usuario);
        } catch (Exception e) {
            usuarioUI.mostrarError("Error al buscar usuario: " + e.getMessage());
        }
    }

    public void buscarUsuarioPorEmail() {
        try {
            String email = usuarioUI.capturarEmail();
            Usuario usuario = usuarioDAO.buscarPorEmail(email);
            usuarioUI.mostrarUsuario(usuario);
        } catch (Exception e) {
            usuarioUI.mostrarError("Error al buscar usuario: " + e.getMessage());
        }
    }

    public void listarTodosLosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.listarTodos();
            System.out.println("DEBUG: Encontrados " + usuarios.size() + " usuarios");
            usuarioUI.mostrarListaUsuarios(usuarios);
        } catch (Exception e) {
            usuarioUI.mostrarError("Error al listar usuarios: " + e.getMessage());
        }
    }

    public void actualizarUsuario() {
        try {
            Long id = usuarioUI.capturarId();
            Usuario usuarioExistente = usuarioDAO.buscarPorId(id);

            if (usuarioExistente == null) {
                usuarioUI.mostrarError("User not found");
                return;
            }

            usuarioUI.mostrarMensaje("Usuario actual:");
            usuarioUI.mostrarUsuario(usuarioExistente);

            usuarioUI.mostrarMensaje("\nIngrese los nuevos datos:");
            Usuario usuarioActualizado = usuarioUI.capturarDatosUsuario();
            usuarioActualizado.setUserId(id);

            if (!validarDatos(usuarioActualizado)) {
                return;
            }

            Usuario usuarioConMismoDocumento = usuarioDAO.buscarPorDocumentId(usuarioActualizado.getDocumentId());
            if (usuarioConMismoDocumento != null && !usuarioConMismoDocumento.getUserId().equals(id)) {
                usuarioUI.mostrarError("Ya existe otro usuario con ese documento de identidad");
                return;
            }

            if (usuarioActualizado.getEmail() != null) {
                Usuario usuarioConMismoEmail = usuarioDAO.buscarPorEmail(usuarioActualizado.getEmail());
                if (usuarioConMismoEmail != null && !usuarioConMismoEmail.getUserId().equals(id)) {
                    usuarioUI.mostrarError("Ya existe otro usuario con ese email");
                    return;
                }
            }

            boolean actualizado = usuarioDAO.actualizar(usuarioActualizado);
            if (actualizado) {
                usuarioUI.mostrarExito("Usuario actualizado exitosamente");
            } else {
                usuarioUI.mostrarError("No se pudo actualizar el usuario");
            }

        } catch (Exception e) {
            usuarioUI.mostrarError("Error al actualizar usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario() {
        try {
            Long id = usuarioUI.capturarId();
            Usuario usuario = usuarioDAO.buscarPorId(id);

            if (usuario == null) {
                usuarioUI.mostrarError("User not found");
                return;
            }

            usuarioUI.mostrarMensaje("Usuario a eliminar:");
            usuarioUI.mostrarUsuario(usuario);

            if (usuarioUI.confirmarAccion("¿Está seguro de eliminar este usuario?")) {
                boolean eliminado = usuarioDAO.eliminar(id);
                if (eliminado) {
                    usuarioUI.mostrarExito("Usuario eliminado exitosamente");
                } else {
                    usuarioUI.mostrarError("No se pudo eliminar el usuario");
                }
            } else {
                usuarioUI.mostrarMensaje("Operación cancelada");
            }

        } catch (Exception e) {
            usuarioUI.mostrarError("Error al eliminar usuario: " + e.getMessage());
        }
    }

    private boolean validarDatos(Usuario usuario) {
        if (!usuario.isValidDocumentId()) {
            usuarioUI.mostrarError("Documento de identidad inválido (mínimo 7 caracteres)");
            return false;
        }

        if (!usuario.isValidFullName()) {
            usuarioUI.mostrarError("Nombre completo no puede estar vacío");
            return false;
        }

        if (usuario.getEmail() != null && !usuario.isValidEmail()) {
            usuarioUI.mostrarError("Email inválido (debe contener @ y .)");
            return false;
        }

        if (usuario.getPhone() != null && !usuario.isValidPhone()) {
            usuarioUI.mostrarError("Teléfono inválido (mínimo 7 caracteres)");
            return false;
        }

        return true;
    }
}