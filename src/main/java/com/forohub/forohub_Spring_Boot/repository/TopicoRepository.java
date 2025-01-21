package com.forohub.forohub_Spring_Boot.repository;

import com.forohub.forohub_Spring_Boot.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    //El metodo sirve para verificar si ya existe un tópico duplicado.
    List<Topico> findByActivoTrue();
    //Verifica si hay un topico activo
}
