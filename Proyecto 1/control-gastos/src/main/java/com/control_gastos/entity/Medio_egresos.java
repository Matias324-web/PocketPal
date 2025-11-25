package com.control_gastos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="medio_egresos")
public class Medio_egresos {

@Id
@Column(name="ID_Medio")
private int id;

@Column(name="Descripcion")
private String descripcion;


public Medio_egresos(){}



public int getId() {
    return id;
}




public void setId(int id) {
    this.id = id;
}




public String getDescripcion() {
    return descripcion;
}




public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}


@Override
public String toString() {
    return "Medio_egresos [id=" + id + ", descripcion=" + descripcion + "]";
}


}
