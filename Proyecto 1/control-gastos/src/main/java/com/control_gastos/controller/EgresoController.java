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

import com.control_gastos.dto.MapasEgreso;
import com.control_gastos.entity.Egresos;
import com.control_gastos.repository.EgresosRepo;
import com.control_gastos.service.ServicioEgresos;

import jakarta.servlet.http.HttpSession;

@Controller
public class EgresoController {

@Autowired
private EgresosRepo egresorepo;

@Autowired
private ServicioEgresos serviegreso;

@Autowired
private HttpSession session;

 @GetMapping("/egresos")
    public String mostrarPaginaEgresos(Model model,@RequestParam(name="FiltroD",required=false)String textobusqueda,@RequestParam(name="categoriaf",required=false)Integer filtroCat, @RequestParam(name="mediof",required=false)Integer filtroMed) {
        
        MapasEgreso mapas=serviegreso.ObtenerListasCatMed();
        List<Egresos>Tabla=serviegreso.TablaEgresos(textobusqueda, filtroCat, filtroMed);

        model.addAttribute("categorias",mapas.categorias());
        model.addAttribute("medios",mapas.medios());
        model.addAttribute("egresos", Tabla);
        

        return "Egresos";
    }


@PostMapping("/GuardarEgreso")
public String GuardarEgreso(@RequestParam("Fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
    @RequestParam("Descripcion") String Descripcion,
    @RequestParam("Categoria") Integer Categoria,
    @RequestParam("Medio") Integer Medio,
    @RequestParam("Monto") float Monto,
    RedirectAttributes redirectAttributes){

         int idusuario=((int)session.getAttribute("usuarioId"));
        Egresos egresonuevo=new Egresos();

        egresonuevo.setFecha(fecha);
        egresonuevo.setDescripcion(Descripcion);
        egresonuevo.setIdcategoria(Categoria);
        egresonuevo.setIdmedio(Medio); 
        egresonuevo.setMonto(Monto);
        egresonuevo.setIdusuario(idusuario);

          try {

             egresorepo.save(egresonuevo);
             redirectAttributes.addFlashAttribute("exitoEgreso","¡Egreso regitrado con Éxito!");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("errorEgreso", "Error al registrar Egreso");

        }

        return "redirect:/egresos";

    }

     @GetMapping("/egresos/eliminar/{id}")
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
    
            egresorepo.deleteById(id);             
           
            redirectAttributes.addFlashAttribute("exito", "¡Egreso eliminado con éxito!");

        } catch (Exception e) {
            
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el Egreso.");
        }
        
     
        return "redirect:/egresos";
    }

    

}
