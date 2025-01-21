package com.forohub.forohub_Spring_Boot.controller;

import com.forohub.forohub_Spring_Boot.model.Curso;
import com.forohub.forohub_Spring_Boot.repository.CursoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    public final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    public ResponseEntity<Curso> registrarCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoRepository.save(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }
}
