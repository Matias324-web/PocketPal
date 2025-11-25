package com.control_gastos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.control_gastos.service.ServicioRegistro;


@Controller
public class RegistroController {

@Autowired
ServicioRegistro serviregistro;



@GetMapping("/Registro")
public String mostrarpaginaregistro(Model model){

    return "Registro";
}

@PostMapping("/GuardaRegistro")
public String GuardarRegistro(@RequestParam("nombre")String nombre,
@RequestParam("apellido")String apellido,
@RequestParam("mail")String mail,
@RequestParam("contraseña")String contraseña,
@RequestParam("moneda")String moneda,
RedirectAttributes redirectAttributes){

boolean res=serviregistro.RegistroNuevo(nombre, apellido, mail, contraseña, moneda);

if(res){
 redirectAttributes.addFlashAttribute("RegistroExitoso", "Su Registro fue realizado con Exito");
} else {
redirectAttributes.addFlashAttribute("RegistroError", "Su Registro no pudo ser Realizado");
}
return "redirect:/login";

}

 



}
