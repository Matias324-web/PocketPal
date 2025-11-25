package com.control_gastos.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="medio_ingresos")
public class Medio {

@Id
@Column(name="ID_Medio")
private int id;

@Column(name="Descripcion")
private String descripcion;

public Medio(){}


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
    return "Medio [id=" + id + ", descripcion=" + descripcion + "]";
}


}
