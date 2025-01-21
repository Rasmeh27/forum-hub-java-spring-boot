package com.forohub.forohub_Spring_Boot.repository;

import com.forohub.forohub_Spring_Boot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
