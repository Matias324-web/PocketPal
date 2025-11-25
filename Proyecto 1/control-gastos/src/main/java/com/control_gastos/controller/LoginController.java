package com.control_gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.control_gastos.entity.Usuario;
import com.control_gastos.service.ServicioLogin;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
    
    @Autowired
    private ServicioLogin servilogin;

   
    @GetMapping({"/","/login"})
    public String mostrarLogin() {
        return "login";
    }

  
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session, 
            RedirectAttributes redirectAttributes) {

        Usuario usuariologin=servilogin.ValidarLogin(username, password);
        if(usuariologin!=null){
            session.setAttribute("usuarioLogueado", usuariologin);
            session.setAttribute("usuarioId", usuariologin.getIdusuario());
            session.setAttribute("usuarioNombre", usuariologin.getNombre());
            session.setAttribute("usuariomoneda", usuariologin.getMoneda());
            return "redirect:/index";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/login";
        }
    }


}
