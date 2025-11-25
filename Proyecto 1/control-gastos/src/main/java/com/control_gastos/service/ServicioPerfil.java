package com.control_gastos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control_gastos.entity.Usuario;
import com.control_gastos.repository.UsuarioRepo;

import jakarta.servlet.http.HttpSession;


@Service
public class ServicioPerfil {
    
   @Autowired
   private UsuarioRepo usuariorepo;

   @Autowired
   HttpSession session;
   
   
   public Usuario DatosUsuario(){
    Usuario user;
    int idusuario=(int)session.getAttribute("usuarioId");
    user=usuariorepo.findById(idusuario);
    return user;
   }

   public boolean ModificarUsuario(Usuario usuarioForm){

    Integer idusuario=(int)session.getAttribute("usuarioId");
    
    /*
    if(idusuario==null){
        return false;
    }
    */

    try {
          
            Usuario usuarioDB = usuariorepo.findById(idusuario).get();
            
            
            usuarioDB.setNombre(usuarioForm.getNombre());
            usuarioDB.setApellido(usuarioForm.getApellido());
            usuarioDB.setMoneda(usuarioForm.getMoneda());
            
          
            usuariorepo.save(usuarioDB);
            
           
            session.setAttribute("usuarioNombre", usuarioDB.getNombre());
            session.setAttribute("usuariomoneda", usuarioDB.getMoneda());
            
            return true;

        } catch (Exception e) {
            return false;
        }

   }

   public boolean ModificarContraseña(String passwordActual,String nuevaPassword,String nuevaPassword2){

     int idusuario=(int)session.getAttribute("usuarioId");
     
     /*
    if(idusuario==null){
        return false;
    }
    */

    Usuario usuarioDB = usuariorepo.findById(idusuario);


        if (!passwordActual.equals(usuarioDB.getConstraseña())) {
            return false;
        }
        
        if (!nuevaPassword.equals(nuevaPassword2)) {
            return false;
        }
        
        usuarioDB.setConstraseña(nuevaPassword);
        usuariorepo.save(usuarioDB);
        return true;

   }

}
