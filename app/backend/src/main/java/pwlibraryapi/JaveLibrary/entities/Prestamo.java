package pwlibraryapi.JaveLibrary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String responsable;

    @NotNull
    private Date fechaprestamo;

    @NotNull
    private Date fechavencimiento;

    @JsonIgnore
    @OneToMany(mappedBy = "prestamo")
    private List<LibroPrestamo> libros;

    @JsonInclude
    @Transient
    private List<Libro> librosPrestamo;

    @Transient
    private String status;

    public Prestamo(int id){
        this.id = id;
    }

    public Prestamo(){}

    public Prestamo(@NotNull String responsable, @NotNull Date fechaprestamo, @NotNull Date fechavencimiento) {
        this.responsable = responsable;
        this.fechaprestamo = fechaprestamo;
        this.fechavencimiento = fechavencimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Date getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Date getFechavencimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public List<LibroPrestamo> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroPrestamo> libros) {
        this.libros = libros;
    }

    public List<Libro> getLibrosPrestamo() {
        return librosPrestamo;
    }

    public void setLibrosPrestamo(List<Libro> librosPrestamo) {
        this.librosPrestamo = librosPrestamo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
