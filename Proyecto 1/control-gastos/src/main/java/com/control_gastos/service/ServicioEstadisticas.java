package com.control_gastos.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control_gastos.dto.MapasIngresoEgreso;
import com.control_gastos.entity.CardsEstadisticas;
import com.control_gastos.entity.Categoria;
import com.control_gastos.entity.Categoria_egresos;
import com.control_gastos.entity.DatosGraficoLinea;
import com.control_gastos.entity.DatosGraficos;
import com.control_gastos.repository.CategoriaEgresoRepo;
import com.control_gastos.repository.CategoriaIngresosRepo;
import com.control_gastos.repository.EgresosRepo;
import com.control_gastos.repository.IngresoRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class ServicioEstadisticas {
    
    @Autowired
    IngresoRepo ingresorepo;

    @Autowired
    EgresosRepo egresorepo;

    @Autowired
    CategoriaIngresosRepo categoriarepo;

    @Autowired
    CategoriaEgresoRepo categoriaegresorepo;

    @Autowired
    HttpSession session;

    public CardsEstadisticas ObtenerCards(Integer MesFiltro){
        CardsEstadisticas Cards= new CardsEstadisticas();
        int idusuario=((int)session.getAttribute("usuarioId"));
        
        if(MesFiltro==null){
            Cards.setIngresostotales(ingresorepo.Ingresototal(idusuario));
            Cards.setEgresostotales(egresorepo.Egresototal(idusuario));
            Cards.setBalance(ingresorepo.Ingresototal(idusuario)-egresorepo.Egresototal(idusuario));
            Cards.setMayoringreso(ingresorepo.mayoringreso(idusuario));
            Cards.setMayorgasto(egresorepo.Mayorgasto(idusuario));
        } else {
            Cards.setIngresostotales(ingresorepo.Totalingresopormes(idusuario, MesFiltro));
            Cards.setEgresostotales(egresorepo.Totalgastopormes(idusuario, MesFiltro));
            Cards.setBalance(ingresorepo.Totalingresopormes(idusuario, MesFiltro)-egresorepo.Totalgastopormes(idusuario, MesFiltro));
            Cards.setMayoringreso(ingresorepo.mayoringresopormes(idusuario, MesFiltro));
            Cards.setMayorgasto(egresorepo.Mayorgastopormes(idusuario, MesFiltro));
        }
               

        return Cards;

    }


    public MapasIngresoEgreso ObtenerMapasCatIngresoEgreso(){

        List<Categoria> categoriasIngreso= categoriarepo.findAll();
        Map<Integer,String>mapacategoriasI=categoriasIngreso.stream()
        .collect(Collectors.toMap(Categoria::getId, Categoria::getDescripcion));

        List<Categoria_egresos>categoriasEgreso= categoriaegresorepo.findAll();
         Map<Integer, String> mapaCategoriasE = categoriasEgreso.stream()
        .collect(Collectors.toMap(Categoria_egresos::getId, Categoria_egresos::getDescripcion));
        
        return new MapasIngresoEgreso(mapacategoriasI,mapaCategoriasE);
    }

    public DatosGraficos GraficoIngresosPorCategoria(Integer MesFiltro){
        int idusuario=(int)session.getAttribute("usuarioId");
        List<String>etiquetasGraficoI=new ArrayList<>();
        List<Float>datosGraficoI=new ArrayList<>();
        List<Object[]>ResultadoRepoI;

        if(MesFiltro==null){
            ResultadoRepoI=ingresorepo.SumarTotalCategoriaIngresos(idusuario);
        } else {
             ResultadoRepoI=ingresorepo.SumarTotalCategoriaIngresospormes(idusuario,MesFiltro);
        }        

        MapasIngresoEgreso mapas=ObtenerMapasCatIngresoEgreso();

        Map<Integer,String> mapacategoriasI=mapas.categoriasIngreso();

        for(Object[] Resultado : ResultadoRepoI ){
            Integer idCategoria=(Integer) Resultado[0];
            String descripcion=mapacategoriasI.get(idCategoria);
            Float monto = ((Number) Resultado[1]).floatValue();

            etiquetasGraficoI.add(descripcion);
            datosGraficoI.add(monto);
        }

        return new DatosGraficos(etiquetasGraficoI, datosGraficoI);
    }

    public DatosGraficos GraficoEgresosPorCategoria(Integer MesFiltro){
         int idusuario=(int)session.getAttribute("usuarioId");
         List<String>etiquetasGraficoE=new ArrayList<>();
         List<Float>datosGraficoE=new ArrayList<>();
         List<Object[]>ResultadorepoE;

         if(MesFiltro==null){
             ResultadorepoE=egresorepo.SumarTotalCategoriaEgresos(idusuario);
         } else {
             ResultadorepoE=egresorepo.SumarTotalCategoriaEgresospormes(idusuario,MesFiltro);
         }
        

         MapasIngresoEgreso mapas=ObtenerMapasCatIngresoEgreso();

         Map<Integer,String>mapacategoriasE=mapas.CategoriasEgreso();

        
        for(Object[] resultado : ResultadorepoE ){
            Integer idcategoria=(Integer) resultado[0];
            String descripcion=mapacategoriasE.get(idcategoria);
            Float monto=((Number)resultado[1]).floatValue();

            etiquetasGraficoE.add(descripcion);
            datosGraficoE.add(monto);
        }
        return new DatosGraficos(etiquetasGraficoE, datosGraficoE);
    }

    public DatosGraficoLinea GraficoIngresosEgresos(){
         int idusuario=(int)session.getAttribute("usuarioId");
         
        List<Object[]>ingresosPormes;
        List<Object[]>egresosPormes;
        ingresosPormes=ingresorepo.sumarIngresosPorMes(idusuario);
        egresosPormes=egresorepo.sumarEgresosPorMes(idusuario);

         Map<String,Double>mapaIngreso=new HashMap<>();
        for(Object[] fila: ingresosPormes){
            String mes=(String) fila[0];
            Double total=((Number)fila[1]).doubleValue();
            mapaIngreso.put(mes, total);
                   
        }

        Map<String,Double> mapaEgreso=new HashMap<>();
        for (Object[] fila: egresosPormes) {
            String mes=(String) fila[0];
            Double total=((Number)fila[1]).doubleValue();
            mapaEgreso.put(mes, total);
            
        }

        List<String>LabelsLinea=new ArrayList<>();
        LocalDate fechaActual=LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM");

        for(int i=11;i>=0;i--){
            LocalDate fecha=fechaActual.minusMonths(i);
            LabelsLinea.add(fecha.format(formatter));
        }


        List<Double>datosIngresoLinea=new ArrayList<>();
        List<Double>datosEgresosLinea= new ArrayList<>();
        
        for(String mes: LabelsLinea){
            datosIngresoLinea.add(mapaIngreso.getOrDefault(mes,0.0));
            datosEgresosLinea.add(mapaEgreso.getOrDefault(mes, 0.0));
        }

        return new DatosGraficoLinea(LabelsLinea, datosIngresoLinea, datosEgresosLinea);

    }




}
