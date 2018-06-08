package pwlibraryapi.JaveLibrary.entities;

public class LibroDevolucion {

    private int libroId;

    private String responsable;

    public LibroDevolucion(){}

    public LibroDevolucion(int libroId, String responsable) {
        this.libroId = libroId;
        this.responsable = responsable;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
