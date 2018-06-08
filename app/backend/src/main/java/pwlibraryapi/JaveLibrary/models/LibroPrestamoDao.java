package pwlibraryapi.JaveLibrary.models;

import org.springframework.data.repository.CrudRepository;
import pwlibraryapi.JaveLibrary.entities.LibroPrestamo;

import javax.validation.constraints.Null;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface LibroPrestamoDao extends CrudRepository<LibroPrestamo, Integer> {
    List<LibroPrestamo> findAllByLibro_IdAndPrestamo_Responsable(int id, String responsable);
    List<LibroPrestamo> findAllByLibro_Id(int id);
}
