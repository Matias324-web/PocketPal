package com.control_gastos.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control_gastos.dto.MapasIngreso;
import com.control_gastos.entity.Categoria;
import com.control_gastos.entity.Ingresos;
import com.control_gastos.entity.Medio;
import com.control_gastos.repository.CategoriaIngresosRepo;
import com.control_gastos.repository.IngresoRepo;
import com.control_gastos.repository.MedioIngresoRepo;

import jakarta.servlet.http.HttpSession;


@Service
public class ServicioIngresos {
    
    @Autowired
    private IngresoRepo ingresorepo;
    @Autowired
    private CategoriaIngresosRepo categoriaRepo;
    @Autowired
    private MedioIngresoRepo mediorepo;
     @Autowired
    private HttpSession session;


    public MapasIngreso ObtenerListasCatMed(){

        
        List<Categoria> categorias = categoriaRepo.findAll();
        List<Medio> medios= mediorepo.findAll();

        Map<Integer,String>mapacategorias=categorias.stream()
        .collect(Collectors.toMap(Categoria::getId, Categoria::getDescripcion));

        Map<Integer,String>mapamedios=medios.stream()
        .collect(Collectors.toMap(Medio::getId,Medio::getDescripcion));

        return new MapasIngreso(mapacategorias, mapamedios);
       
    }



    public List<Ingresos>TablaIngresos(String textobusqueda,Integer filtroCat,Integer filtroMed){
        int idusuario=(int)session.getAttribute("usuarioId");
        List<Ingresos>Ingresos=ingresorepo.Tablaconfiltros(idusuario,textobusqueda, filtroCat, filtroMed);
        return Ingresos;
    }
    

}
