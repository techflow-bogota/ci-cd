package pwlibraryapi.JaveLibrary.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "libroprestamo")
public class LibroPrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;

    private Date fechadevolucion;

    @Transient
    private String status;

    public LibroPrestamo (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
