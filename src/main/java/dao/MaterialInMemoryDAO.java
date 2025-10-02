package dao;

import model.Item;
import java.util.*;
import java.util.stream.Collectors;

public class MaterialInMemoryDAO implements MaterialDAO {

    private Map<Long, Item> materiales = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Long insertar(Item material) {
        Long nuevoId = nextId++;
        material.setMaterialId(nuevoId);
        materiales.put(nuevoId, material);
        return nuevoId;
    }

    @Override
    public Item buscarPorId(Long materialId) {
        return materiales.get(materialId);
    }

    @Override
    public List<Item> buscarPorTitulo(String titulo) {
        return materiales.values().stream()
                .filter(material -> material.getTitle() != null &&
                       material.getTitle().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> buscarPorAutor(String autor) {
        return materiales.values().stream()
                .filter(material -> material.getAuthor() != null &&
                       material.getAuthor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> buscarPorTipo(String tipo) {
        return materiales.values().stream()
                .filter(material -> material.getType().equals(tipo))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> listarTodos() {
        return new ArrayList<>(materiales.values());
    }

    @Override
    public List<Item> listarDisponibles() {
        // Por ahora todos están disponibles (sin sistema de préstamos)
        return listarTodos();
    }

    @Override
    public boolean actualizar(Item material) {
        if (material.getMaterialId() == null) {
            return false;
        }

        if (!materiales.containsKey(material.getMaterialId())) {
            return false;
        }

        materiales.put(material.getMaterialId(), material);
        return true;
    }

    @Override
    public boolean eliminar(Long materialId) {
        Item eliminado = materiales.remove(materialId);
        return eliminado != null;
    }

    @Override
    public boolean existeTitulo(String titulo) {
        return materiales.values().stream()
                .anyMatch(material -> material.getTitle().equalsIgnoreCase(titulo));
    }
}