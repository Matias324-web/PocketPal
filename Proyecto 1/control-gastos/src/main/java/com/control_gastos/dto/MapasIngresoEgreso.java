package com.control_gastos.dto;

import java.util.Map;

public record  MapasIngresoEgreso ( Map<Integer, String> categoriasIngreso, 
    Map<Integer, String> CategoriasEgreso) {


}
