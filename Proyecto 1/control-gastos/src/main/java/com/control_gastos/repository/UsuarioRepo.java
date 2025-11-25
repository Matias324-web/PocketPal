package com.control_gastos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control_gastos.entity.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario,Integer> {

    Optional<Usuario>findByIdusuario(int idusuario);

    Optional<Usuario>findByMail(String mail);

    public Usuario findById(int idusuario);


}
