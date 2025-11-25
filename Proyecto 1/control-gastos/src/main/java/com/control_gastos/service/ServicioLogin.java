package com.control_gastos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control_gastos.entity.Usuario;
import com.control_gastos.repository.UsuarioRepo;

@Service
public class ServicioLogin {
    
    @Autowired
    UsuarioRepo usuariorepo;

    public Usuario ValidarLogin(String username,String password){
        Usuario usuario = null;

        
        try {
            int id = Integer.parseInt(username);
            usuario = usuariorepo.findByIdusuario(id).orElse(null);
        } catch (NumberFormatException e) {
            usuario = usuariorepo.findByMail(username).orElse(null);
        }

        
        if (usuario == null) {
            return null; 
        }

       
        if (!password.equals(usuario.getConstraseña())) {
            return null; 
        }

        
        return usuario;
    }
}


