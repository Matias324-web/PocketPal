package com.control_gastos.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name="ingresos")
public class Ingresos {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ID_Ingresos")
int id;

@Column(name="Fecha")
@NotNull(message="Debe Seleccionar una fecha")
@PastOrPresent(message="La fecha no puede ser futura")
Date fecha;

@Column(name="Descripcion")
String descripcion;

@Column(name="ID_Categoria")
@NotNull(message="Debe eleccionar una Categoria")
int id_categoria;

@Column(name="ID_Medio")
@NotNull(message="Debe eleccionar una Categoria")
int id_medio;

@Column(name="Monto")
@NotNull(message="El monto no puede ser nulo")
@Min(value=1,message="El monto debe ser mayor a 0")
float monto;

@Column(name="ID_Usuario")
int idusuario;

public Ingresos(){}

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public Date getFecha() {
    return fecha;
}

public void setFecha(Date fecha) {
    this.fecha = fecha;
}

public String getDescripcion() {
    return descripcion;
}

public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}

public int getId_categoria() {
    return id_categoria;
}

public void setId_categoria(int id_categoria) {
    this.id_categoria = id_categoria;
}

public int getId_medio() {
    return id_medio;
}

public void setId_medio(int id_medio) {
    this.id_medio = id_medio;
}

public float getMonto() {
    return monto;
}

public void setMonto(float monto) {
    this.monto = monto;
}

public int getidusuario() {
    return idusuario;
}

public void setidusuario(int id_usuario) {
    this.idusuario = id_usuario;
}

@Override
public String toString() {
    return "Ingresos [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion + ", id_categoria=" + id_categoria
            + ", id_medio=" + id_medio + ", monto=" + monto + ", id_usuario=" + idusuario + "]";
}



}
