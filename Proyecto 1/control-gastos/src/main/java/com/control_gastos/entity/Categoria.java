package com.control_gastos.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="categoria_ingresos")
public class Categoria {

    @Id
    @Column(name="ID_Categoria")
    private int id;

    @Column(name="Descripcion")
    private String descripcion;

    
    

    public Categoria(){}


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
        return "Categoria [id=" + id + ", descripcion=" + descripcion + "]";
    }


}
