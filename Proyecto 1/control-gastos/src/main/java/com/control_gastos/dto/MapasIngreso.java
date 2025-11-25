package com.control_gastos.dto;

import java.util.Map;

public record MapasIngreso(  
    Map<Integer, String> categorias, 
    Map<Integer, String> medios){
    
}
