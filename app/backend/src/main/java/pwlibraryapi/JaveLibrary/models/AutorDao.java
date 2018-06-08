package pwlibraryapi.JaveLibrary.models;


import org.springframework.data.jpa.repository.JpaRepository;
import pwlibraryapi.JaveLibrary.entities.Autor;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface AutorDao extends CrudRepository<Autor, Integer> {
    Autor findByNombre(String nombre);

}
