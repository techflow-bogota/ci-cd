package pwlibraryapi.JaveLibrary.controllers;

import pwlibraryapi.JaveLibrary.entities.Usuario;
import pwlibraryapi.JaveLibrary.models.UsuarioDao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioController {

    public Usuario createUsuario(Usuario usuario, UsuarioDao usuarioDao){
        Usuario user = usuarioDao.findByUsuario(usuario.getUsuario());
        if(user == null){
            user = new  Usuario();
            user.setNombres(usuario.getNombres());
            user.setApellidos(usuario.getApellidos());
            user.setUsuario(usuario.getUsuario());
            user.setTipo(usuario.getTipo());
            user.setDocumento(usuario.getDocumento());
            user.setPassword(hashPassword(usuario.getPassword()));
            user.setStatus("200");
            usuarioDao.save(user);
            return user;
        }
        user = new Usuario();
        user.setStatus("406");
        return user;
    }

    public void updateUsuario (Usuario usuario , Usuario userToUpdate){
        userToUpdate.setUsuario(usuario.getUsuario());
        userToUpdate.setNombres(usuario.getNombres());
        userToUpdate.setApellidos(usuario.getApellidos());
        userToUpdate.setTipo(usuario.getTipo());
        userToUpdate.setDocumento(usuario.getDocumento());

    }

    private String hashPassword (String password){
        String hashPassword = null;
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            sha256.update(password.getBytes("UTF-8"));
            byte[] digest = sha256.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte element : digest) {
                stringBuffer.append(String.format("%02x", element));
            }
            hashPassword = stringBuffer.toString();
        }
        catch (NoSuchAlgorithmException event) {
            System.out.println("Error: [" + event.getMessage() + "]");
        }
        catch (UnsupportedEncodingException event) {
            System.out.println("Error: [" + event.getMessage() + "]");
        }
        return hashPassword;
    }

    public Usuario validarUsuario (Usuario usuario, UsuarioDao usuarioDao){
        Usuario usuarioToLogin = usuarioDao.findByUsuario(usuario.getUsuario());
        String verifyPassword = hashPassword(usuario.getPassword());
        if(usuarioToLogin!=null && verifyPassword.equals(usuarioToLogin.getPassword())){
            usuarioToLogin.setStatus("200");
            return usuarioToLogin;
        }
        usuarioToLogin = new Usuario();
        usuarioToLogin.setStatus("404");
        return usuarioToLogin;
    }
}
