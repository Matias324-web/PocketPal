package com.control_gastos.entity;

import java.util.List;

public class DatosGraficos {
    private List<String> etiquetas;
    private List<Float> datos;

    public DatosGraficos(List<String> etiquetas, List<Float> datos) {
        this.etiquetas = etiquetas;
        this.datos = datos;
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public List<Float> getDatos() {
        return datos;
    }
    
}
