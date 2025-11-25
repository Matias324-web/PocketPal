package com.control_gastos.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control_gastos.dto.MapasEgreso;
import com.control_gastos.entity.Categoria_egresos;
import com.control_gastos.entity.Egresos;
import com.control_gastos.entity.Medio_egresos;
import com.control_gastos.repository.CategoriaEgresoRepo;
import com.control_gastos.repository.EgresosRepo;
import com.control_gastos.repository.MedioEgresoRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class ServicioEgresos {
    
    @Autowired
    private EgresosRepo egresorepo;
    @Autowired
    private CategoriaEgresoRepo categoriarepo;
    @Autowired
    private MedioEgresoRepo mediorepo;
    @Autowired
    HttpSession session;


    public MapasEgreso ObtenerListasCatMed(){

        List<Categoria_egresos>categorias= categoriarepo.findAll();
        List<Medio_egresos> medios=mediorepo.findAll();

        Map<Integer, String> mapaCategorias = categorias.stream()
        .collect(Collectors.toMap(Categoria_egresos::getId, Categoria_egresos::getDescripcion));

        Map<Integer, String> mapaMedios = medios.stream()
        .collect(Collectors.toMap(Medio_egresos::getId, Medio_egresos::getDescripcion));

        return new MapasEgreso(mapaCategorias, mapaMedios);
    }

    public List<Egresos> TablaEgresos(String textobusqueda, Integer filtroCat, Integer filtroMed){
        int idusuario=(int)session.getAttribute("usuarioId");
        List<Egresos>Egresos=egresorepo.Tablaconfiltros(idusuario, textobusqueda, filtroCat, filtroMed);
        return Egresos;
    }

}
