package com.control_gastos.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categoria_egresos")
public class Categoria_egresos {

@Id
@Column(name="ID_Categoria")
private int id;

@Column(name="Descripcion")
private String descripcion;


public Categoria_egresos(){}

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
    return "Categoria_egresos [id=" + id + ", descripcion=" + descripcion + "]";
}



}
