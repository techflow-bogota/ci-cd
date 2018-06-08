package pwlibraryapi.JaveLibrary.controllers;

import org.springframework.web.bind.annotation.*;
import pwlibraryapi.JaveLibrary.entities.*;
import pwlibraryapi.JaveLibrary.models.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@CrossOrigin
@RestController
public class LibraryController {

    @Autowired
    private AutorDao autorDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private LibroDao libroDao;

    @Autowired
    private PrestamoDao prestamoDao;

    @Autowired
    private LibroPrestamoDao libroPrestamoDao;

    @GetMapping(value = "/libro/{id}")
    public Libro getLibro(@PathVariable Integer id){
        Libro libro = libroDao.findById(id).orElseGet(()->{
            Libro libroTemporal = new Libro();
            libroTemporal.setStatus("404");
            return libroTemporal;
        });
        libro.setStatus("200");
        return libro;
    }

    @PostMapping(value = "/libro")
    public Libro createLibro(@RequestBody Libro libro){
        AutorController autorController = new AutorController();
        LibroController libroController = new LibroController();
        List<Autor> autores = autorController.createAutors(libro.getAutores(), autorDao);
        Libro libroToCreate = libroController.createLibro(libro, autores);
        libroDao.save(libroToCreate);

        return libroToCreate;
    }

    @PutMapping(value = "/libro")
    public Libro updateLibro(@RequestBody Libro libro){
        Libro libroToUpdate = libroDao.findById(libro.getId()).orElseGet(()->{
            Libro libroTemporal = new Libro();
            libroTemporal.setStatus("404");
            return libroTemporal;
        });
        LibroController libroController = new LibroController();
        libroController.updateLibro(libro, libroToUpdate);
        libroDao.save(libroToUpdate);
        libroToUpdate.setStatus("200");
        return libroToUpdate;
    }

    @DeleteMapping(value = "/libro")
    public Libro deleteLibro(@RequestBody Libro libro){

        libroDao.deleteById(libro.getId());
        libro.setStatus("200");
        return libro;
    }

    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable Integer id){
        Usuario usuario = usuarioDao.findById(id).orElseGet(()-> {
            Usuario usuarioTemporal = new Usuario();
            usuarioTemporal.setStatus("404");
            return usuarioTemporal;
        });
        usuario.setStatus("200");
        return usuario;
    }

    @PostMapping(value = "/login")
    public Usuario loginUsuario (@RequestBody Usuario usuario){
        return new UsuarioController().validarUsuario(usuario, usuarioDao);
    }
    @PostMapping(value = "/registro")
    public Usuario registrarUsuario(@RequestBody Usuario usuario){
        Usuario user = new UsuarioController().createUsuario(usuario, usuarioDao);
        return user;
    }

    @PutMapping(value = "/usuario")
    public Usuario updateUsuario(@RequestBody Usuario usuario){
        Usuario userToUpdate = usuarioDao.findById(usuario.getId()).orElseGet(()->{
            Usuario usuarioTemporal = new Usuario();
            usuarioTemporal.setStatus("404");
            return usuarioTemporal;
        });
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.updateUsuario(usuario, userToUpdate);
        usuarioDao.save(userToUpdate);
        userToUpdate.setStatus("200");
        return userToUpdate;
    }

    @DeleteMapping(value = "/usuario")
    public Usuario deleteUsuario(@RequestBody Usuario usuario){
        usuarioDao.deleteById(usuario.getId());
        usuario.setStatus("200");
        return usuario;
    }


    @GetMapping(value = "/autor/{id}")
    public Autor getAutor(@PathVariable Integer id){
        Autor autor = autorDao.findById(id).orElseGet(()->{
            Autor autorTemporal =new Autor();
            autorTemporal.setStatus("404");
            return autorTemporal;
        });
        autor.setStatus("200");
        return autor;
    }

    @GetMapping(value = "/usuarios")
    public Collection<Usuario> getUsuarios(){
        return (Collection<Usuario>) usuarioDao.findAll();
    }

    @GetMapping(value = "/libros")
    public Collection<Libro> getLibros(){
        return (Collection<Libro>) libroDao.findAll();
    }

    @GetMapping(value = "/autores")
    public Collection<Autor> getAutores(){
        return (Collection<Autor>) autorDao.findAll();
    }

    @GetMapping(value = "/prestamo/{id}")
    public Prestamo getPrestamo(@PathVariable Integer id){
        Prestamo prestamo = prestamoDao.findById(id).orElseGet(()->{
            Prestamo prestamoTemporal = new Prestamo();
            prestamoTemporal.setStatus("404");
            return prestamoTemporal;
        });
        prestamo.setStatus("200");
        return prestamo;
    }

    @PostMapping(value = "/prestamo")
    public Prestamo createPrestamo(@RequestBody Prestamo prestamo){
        PrestamoController prestamoController = new PrestamoController();
        LibroController libroController = new LibroController();
        LibroPrestamoController libroPrestamoController = new LibroPrestamoController();
        Prestamo prestamoToCreate = prestamoController.createPrestamo(prestamo);
        List<Libro> librosPrestamo = libroController.librosPrestamo(prestamo,libroDao);
        prestamoToCreate.setLibrosPrestamo(librosPrestamo);
        libroPrestamoController.createPrestamo(librosPrestamo, prestamoToCreate, libroPrestamoDao);
        prestamoToCreate.setStatus("200");
        return prestamoToCreate;
    }

    @PutMapping(value = "/prestamo")
    public LibroPrestamo updatePrestamo (@RequestBody LibroDevolucion libroDevolucion){
        LibroPrestamoController libroPrestamoController = new LibroPrestamoController();
        Libro libro = libroDao.findById(libroDevolucion.getLibroId()).orElse(null);
        LibroPrestamo libroPrestamoUpdate = new LibroPrestamo();
        if(libro == null){
            LibroPrestamo libroPrestamoTemporal = new LibroPrestamo();
            libroPrestamoTemporal.setStatus("404");
            return libroPrestamoTemporal;
        }else if(!libro.getDisponible()){
            List<LibroPrestamo> libroPrestamo = libroPrestamoDao.findAllByLibro_IdAndPrestamo_Responsable(libroDevolucion.getLibroId(), libroDevolucion.getResponsable());
            libroPrestamoUpdate =libroPrestamoController.updateDevolucion(libroPrestamo,libroPrestamoDao, libro, libroDao);
            return libroPrestamoUpdate;
        }
        libroPrestamoUpdate.setStatus("406");
        return libroPrestamoUpdate;
    }

    @GetMapping(value = "/historial/{id}")
    public List<LibroPrestamo> getHistorialPrestamo (@PathVariable Integer id){
        List<LibroPrestamo> libroPrestamos = libroPrestamoDao.findAllByLibro_Id(id);
        return libroPrestamos;
    }
}
