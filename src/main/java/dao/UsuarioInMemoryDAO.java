package dao;

import model.Usuario;
import java.util.*;

public class UsuarioInMemoryDAO implements UsuarioDAO {

    private Map<Long, Usuario> usuarios = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Long insertar(Usuario usuario) {
        Long nuevoId = nextId++;
        usuario.setUserId(nuevoId);
        usuarios.put(nuevoId, usuario);
        return nuevoId;
    }

    @Override
    public Usuario buscarPorId(Long userId) {
        return usuarios.get(userId);
    }

    @Override
    public Usuario buscarPorDocumentId(String documentId) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getDocumentId().equals(documentId)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getEmail() != null && usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        if (usuario.getUserId() == null) {
            return false;
        }

        if (!usuarios.containsKey(usuario.getUserId())) {
            return false;
        }

        usuarios.put(usuario.getUserId(), usuario);
        return true;
    }

    @Override
    public boolean eliminar(Long userId) {
        Usuario eliminado = usuarios.remove(userId);
        return eliminado != null;
    }

    @Override
    public boolean existeDocumentId(String documentId) {
        return buscarPorDocumentId(documentId) != null;
    }

    @Override
    public boolean existeEmail(String email) {
        return buscarPorEmail(email) != null;
    }
}