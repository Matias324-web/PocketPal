package com.control_gastos.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="egresos")
public class Egresos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Egresos")
    int id;

    @Column(name="Fecha")
    Date fecha;

    @Column(name="Descripcion")
    String descripcion;

    @Column(name="ID_Categoria")
    int idcategoria;

    @Column(name="ID_Medio")
    int idmedio;

    @Column(name="Monto")
    float monto;

    @Column(name="ID_Usuario")
    int idusuario;

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

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public int getIdmedio() {
        return idmedio;
    }

    public void setIdmedio(int idmedio) {
        this.idmedio = idmedio;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public String toString() {
        return "Egresos [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion + ", idcategoria="
                + idcategoria + ", idmedio=" + idmedio + ", monto=" + monto + ", idusuario=" + idusuario + "]";
    }


}
