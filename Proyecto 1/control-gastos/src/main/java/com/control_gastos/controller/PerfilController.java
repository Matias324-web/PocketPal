package com.control_gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.control_gastos.entity.Usuario;
import com.control_gastos.service.ServicioPerfil;

import jakarta.servlet.http.HttpSession;

@Controller
public class PerfilController {

    @Autowired
    private ServicioPerfil serviperfil;


 @GetMapping("/perfil")
 public String mostrarpaginaPerfil(Model model,HttpSession session){
    
    Usuario user=serviperfil.DatosUsuario();
    model.addAttribute("usuario",user);
    return "perfil";
 }

 @PostMapping("/perfil/actualizar")
    public String actualizarPerfil(@ModelAttribute("usuario") Usuario usuarioForm, 
                                   HttpSession session, 
                                   RedirectAttributes redirectAttributes) {
        
        boolean res=serviperfil.ModificarUsuario(usuarioForm);
        if(res){
            redirectAttributes.addAttribute("exito","Perfil Actualizado Con Exito");
        }else{
             redirectAttributes.addAttribute("error","No Se pudo Actualizar el perfil");
        }

        return "redirect:/perfil";
    }

    
    @PostMapping("/perfil/cambiar-password")
    public String cambiarPassword(
            @RequestParam("passwordActual") String passwordActual,
            @RequestParam("nuevaPassword") String nuevaPassword,
            @RequestParam("confirmarPassword") String nuevaPassword2,
            HttpSession session, 
            RedirectAttributes redirectAttributes) {
        
        boolean res=serviperfil.ModificarContraseña(passwordActual, nuevaPassword, nuevaPassword2);
        
        if (res==false){
            redirectAttributes.addFlashAttribute("error", "Las nuevas contraseñas no coinciden.");
            return "redirect:/perfil";
        }
        redirectAttributes.addFlashAttribute("exito", "¡Contraseña actualizada con éxito!");
        return "redirect:/perfil";
    }


}
