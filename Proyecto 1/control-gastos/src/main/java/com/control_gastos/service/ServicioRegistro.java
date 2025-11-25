package com.control_gastos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control_gastos.entity.Usuario;
import com.control_gastos.repository.UsuarioRepo;

@Service
public class ServicioRegistro {
    
    @Autowired
    UsuarioRepo usuariorepo;

    
    public boolean RegistroNuevo(String nombre,String apellido,String mail,String contraseña,String moneda){

    Usuario usuarionuevo= new Usuario();

    usuarionuevo.setNombre(nombre);
    usuarionuevo.setApellido(apellido);
    usuarionuevo.setMail(mail);
    usuarionuevo.setConstraseña(contraseña);
    usuarionuevo.setMoneda(moneda);
    usuarionuevo.setFecha(new Date());

     try {
        
        usuariorepo.save(usuarionuevo);
        return true;

    } catch (Exception e) {
        return false;
    }

    }

    
}
