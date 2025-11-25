package com.control_gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control_gastos.entity.Categoria_egresos;

public interface CategoriaEgresoRepo extends JpaRepository<Categoria_egresos, Integer> {

}
