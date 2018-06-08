package pwlibraryapi.JaveLibrary.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="autor")
public class Autor {

    @JsonIgnore
    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String nombre;

    @Transient
    private String status;

    public Autor(){}

    public Autor(int id){
        this.id = id;
    }

    public Autor(@NotNull String nombre) {
        this.nombre = nombre;
    }

    public Autor(ArrayList<Libro> libros, @NotNull String nombre) {
        this.libros = libros;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

