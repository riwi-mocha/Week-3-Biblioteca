package dao;

import model.Item;
import java.util.List;

public interface MaterialDAO {

    Long insertar(Item material);

    Item buscarPorId(Long materialId);

    List<Item> buscarPorTitulo(String titulo);

    List<Item> buscarPorAutor(String autor);

    List<Item> buscarPorTipo(String tipo);

    List<Item> listarTodos();

    List<Item> listarDisponibles();

    boolean actualizar(Item material);

    boolean eliminar(Long materialId);

    boolean existeTitulo(String titulo);
}