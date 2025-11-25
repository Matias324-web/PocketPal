package com.control_gastos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.control_gastos.entity.Medio;
public interface MedioIngresoRepo extends JpaRepository<Medio, Integer> {

}
