
package com.control_gastos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.control_gastos.entity.CardsEstadisticas;
import com.control_gastos.entity.DatosGraficoLinea;
import com.control_gastos.entity.DatosGraficos;
import com.control_gastos.service.ServicioEstadisticas;




@Controller 
public class EstadisticasController {

    @Autowired
    private ServicioEstadisticas serviestadisticas;

    

    @GetMapping("/estadisticas")
    public String mostrarPaginaEgresos(Model model,@RequestParam (name="FiltroMes",required=false)Integer mesfiltro) {

        CardsEstadisticas Cards;
        Cards=serviestadisticas.ObtenerCards(mesfiltro);

        DatosGraficos GraficoIngresos;
        GraficoIngresos=serviestadisticas.GraficoIngresosPorCategoria(mesfiltro);

        DatosGraficos GraficoEgresos;
        GraficoEgresos=serviestadisticas.GraficoEgresosPorCategoria(mesfiltro);

        DatosGraficoLinea GraficoLineal;
        GraficoLineal=serviestadisticas.GraficoIngresosEgresos();
     
        
        


        model.addAttribute("ingresostotales", Cards.getIngresostotales());
        model.addAttribute("egresostotales",Cards.getEgresostotales());
        model.addAttribute("balance", Cards.getBalance());
        model.addAttribute("mayorgasto",Cards.getMayorgasto());
        model.addAttribute("mayoringreso",Cards.getMayoringreso());
         

        model.addAttribute("etiquetagrafico",GraficoIngresos.getEtiquetas());
        model.addAttribute("datosgrafico", GraficoIngresos.getDatos());

        model.addAttribute("etiquetasgraficoe",GraficoEgresos.getEtiquetas());
        model.addAttribute("datosgraficoe", GraficoEgresos.getDatos());


        model.addAttribute("labelslineas", GraficoLineal.getLabels());
        model.addAttribute("datosingresolinea",GraficoLineal.getDatosIngresos());
        model.addAttribute("datosegresolinea", GraficoLineal.getDatosEgresos());


    
        return "Estadisticas";
    }
    
}