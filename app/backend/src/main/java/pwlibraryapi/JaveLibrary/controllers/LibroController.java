package pwlibraryapi.JaveLibrary.controllers;

import pwlibraryapi.JaveLibrary.entities.Autor;
import pwlibraryapi.JaveLibrary.entities.Libro;
import pwlibraryapi.JaveLibrary.entities.Prestamo;
import pwlibraryapi.JaveLibrary.models.LibroDao;

import java.util.ArrayList;
import java.util.List;

public class LibroController {

    public Libro createLibro (Libro libroToCreate, List<Autor> autores){
        Libro libro = new Libro();
        libro.setNombre(libroToCreate.getNombre());
        libro.setIsbn(libroToCreate.getIsbn());
        libro.setDisponible(true);
        libro.setAutores(autores);
        libro.setStatus("200");
        return libro;
    }

    public void updateLibro (Libro libro , Libro libroToUpdate){
        libroToUpdate.setNombre(libro.getNombre());
        libroToUpdate.setIsbn(libro.getIsbn());
        libroToUpdate.setDisponible(libro.getDisponible());
        libroToUpdate.setAutores(libro.getAutores());
    }

    public List<Libro> librosPrestamo (Prestamo prestamo, LibroDao libroDao){
        List<Libro> libros = new ArrayList<>();
        for(Libro libro : prestamo.getLibrosPrestamo()){
            Libro libroPrestamo = libroDao.findById(libro.getId()).orElse(null);
            libroPrestamo.setDisponible(false);
            libroDao.save(libroPrestamo);
            libros.add((libroPrestamo));
        }
        return libros;
    }


}
