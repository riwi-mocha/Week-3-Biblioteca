package dao;

import model.Usuario;
import java.util.List;

public interface UsuarioDAO {

    Long insertar(Usuario usuario);

    Usuario buscarPorId(Long userId);

    Usuario buscarPorDocumentId(String documentId);

    Usuario buscarPorEmail(String email);

    List<Usuario> listarTodos();

    boolean actualizar(Usuario usuario);

    boolean eliminar(Long userId);

    boolean existeDocumentId(String documentId);

    boolean existeEmail(String email);
}