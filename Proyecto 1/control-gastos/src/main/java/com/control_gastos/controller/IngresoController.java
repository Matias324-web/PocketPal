package com.control_gastos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.control_gastos.dto.MapasIngreso;
import com.control_gastos.entity.Ingresos;
import com.control_gastos.repository.IngresoRepo;
import com.control_gastos.service.ServicioIngresos;

import jakarta.servlet.http.HttpSession;

@Controller
public class IngresoController {

    @Autowired
    private IngresoRepo ingresorepo;

    @Autowired
    private ServicioIngresos serviingreso;

    @Autowired
    private HttpSession session;


    
    @GetMapping("/ingresos")
    public String mostrarPaginaIngresos(Model model,@RequestParam (name="FiltroD",required=false)String textobusqueda,@RequestParam(name="categoriaf",required=false)Integer filtroCat, @RequestParam(name="mediof",required=false)Integer filtroMed) {
       

        MapasIngreso mapas=serviingreso.ObtenerListasCatMed();
        List<Ingresos> Tabla=serviingreso.TablaIngresos(textobusqueda, filtroCat, filtroMed);

        model.addAttribute("categorias",mapas.categorias());
        model.addAttribute("medios", mapas.medios());    
        model.addAttribute("ingresos", Tabla);
 
        return "Ingresos";
    }

    @PostMapping("/GuardarIngreso")
    public String guardarIngreso(
            @RequestParam("Fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @RequestParam("Descripcion") String Descripcion,
            @RequestParam("Categoria") Integer Categoria,
            @RequestParam("Medio") Integer Medio,
            @RequestParam("Monto") float Monto,
            RedirectAttributes redirectAttributes ) {

        int idusuario = ((int) session.getAttribute("usuarioId"));

        Ingresos ingresonuevo = new Ingresos();

        ingresonuevo.setFecha(fecha);
        ingresonuevo.setDescripcion(Descripcion);
        ingresonuevo.setId_categoria(Categoria);
        ingresonuevo.setId_medio(Medio);
        ingresonuevo.setMonto(Monto);
        ingresonuevo.setidusuario(idusuario);

        try {

             ingresorepo.save(ingresonuevo);
            redirectAttributes.addFlashAttribute("exitoIngreso", "¡Ingreso registrado con éxito!");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("errorIngreso", "Error al registrar ingreso");

        }

       

        return "redirect:/ingresos";

    }

    @GetMapping("/ingresos/eliminar/{id}")
    public String eliminarIngreso(
            @PathVariable("id") Integer id, 
            HttpSession session,         
            RedirectAttributes redirectAttributes 
    ) {

        Integer idusuario = (Integer) session.getAttribute("usuarioId");
        if (idusuario == null) {
            return "redirect:/login";
        }

        try {
           
            
            ingresorepo.deleteById(id); 
            
           
            redirectAttributes.addFlashAttribute("exitoEliminar", "¡Ingreso eliminado con éxito!");

        } catch (Exception e) {
            
            redirectAttributes.addFlashAttribute("errorEliminar", "Error al eliminar el ingreso.");
        }
        
     
        return "redirect:/ingresos";
    }

}
