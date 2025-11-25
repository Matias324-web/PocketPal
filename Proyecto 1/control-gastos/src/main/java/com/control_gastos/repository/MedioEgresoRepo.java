package com.control_gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control_gastos.entity.Medio_egresos;

public interface MedioEgresoRepo extends JpaRepository<Medio_egresos,Integer> {

}
