package pwlibraryapi.JaveLibrary.controllers;

import pwlibraryapi.JaveLibrary.entities.Autor;
import pwlibraryapi.JaveLibrary.entities.Libro;
import pwlibraryapi.JaveLibrary.models.AutorDao;

import java.util.ArrayList;
import java.util.List;

public class AutorController {

    public Autor createAutor (Autor autor, ArrayList<Libro> librosAutor){
        Autor autorToCreate = new Autor();
        autorToCreate.setNombre(autor.getNombre());
        return autorToCreate;
    }

    public List<Autor> createAutors(List<Autor> autoresTocreate, AutorDao autorDao){
        List<Autor> autores = new ArrayList<>();
        for(Autor autor : autoresTocreate){
            Autor autorToCreate = autorDao.findByNombre(autor.getNombre());
            if(autorToCreate==null){
                autorToCreate = new Autor(autor.getNombre());
            }
            autores.add(autorToCreate);

        }
        return autores;
    }






}
