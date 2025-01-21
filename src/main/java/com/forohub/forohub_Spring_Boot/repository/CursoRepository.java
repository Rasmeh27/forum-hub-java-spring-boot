package com.forohub.forohub_Spring_Boot.repository;

import com.forohub.forohub_Spring_Boot.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
