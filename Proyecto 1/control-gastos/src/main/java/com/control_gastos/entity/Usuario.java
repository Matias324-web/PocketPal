package com.control_gastos.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Usuario")
    int idusuario;

    @Column(name="Mail")
    String mail;

    @Column(name="Contraseña")
    String contraseña;

    @Column(name="Nombre")
    String nombre;

    @Column(name="Apellido")
    String apellido;

    @Column(name="Moneda")
    String Moneda;

    @Column(name="Fecha_registro")
    @NotNull(message="Debe Seleccionar una fecha")
    @PastOrPresent(message="La fecha no puede ser futura")
    Date fecha;

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail1) {
        mail = mail1;
    }

    public String getConstraseña() {
        return contraseña;
    }

    public void setConstraseña(String constraseña) {
        this.contraseña = constraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String moneda) {
        Moneda = moneda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Usuario [idusuario=" + idusuario + ", Mail=" + mail + ", constraseña=" + contraseña + ", nombre="
                + nombre + ", apellido=" + apellido + ", Moneda=" + Moneda + ", fecha=" + fecha + "]";
    } 



    
}
