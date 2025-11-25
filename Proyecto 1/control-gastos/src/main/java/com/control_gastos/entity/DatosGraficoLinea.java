package com.control_gastos.entity;

import java.util.List;

public class DatosGraficoLinea {
    private List<String> labels;
    private List<Double> datosIngresos;
    private List<Double> datosEgresos;

    public DatosGraficoLinea(List<String> labels, List<Double> datosIngresos, List<Double> datosEgresos) {
        this.labels = labels;
        this.datosIngresos = datosIngresos;
        this.datosEgresos = datosEgresos;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Double> getDatosIngresos() {
        return datosIngresos;
    }

    public void setDatosIngresos(List<Double> datosIngresos) {
        this.datosIngresos = datosIngresos;
    }

    public List<Double> getDatosEgresos() {
        return datosEgresos;
    }

    public void setDatosEgresos(List<Double> datosEgresos) {
        this.datosEgresos = datosEgresos;
    }

    
}
