package com.control_gastos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.control_gastos.entity.Categoria;

public interface CategoriaIngresosRepo extends JpaRepository<Categoria,Integer> {

}
