package pwlibraryapi.JaveLibrary.models;

import org.springframework.data.repository.CrudRepository;
import pwlibraryapi.JaveLibrary.entities.Prestamo;


public interface PrestamoDao extends CrudRepository<Prestamo, Integer> {
}
