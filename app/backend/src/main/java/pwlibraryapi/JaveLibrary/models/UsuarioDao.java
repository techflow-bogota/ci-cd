package pwlibraryapi.JaveLibrary.models;

import org.springframework.data.repository.CrudRepository;
import pwlibraryapi.JaveLibrary.entities.Usuario;

import javax.transaction.Transactional;

@Transactional
public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
    Usuario findByUsuario (String usuario);
}
