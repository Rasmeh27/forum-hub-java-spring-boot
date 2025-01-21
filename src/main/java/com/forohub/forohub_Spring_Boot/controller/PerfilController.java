package com.forohub.forohub_Spring_Boot.controller;

import com.forohub.forohub_Spring_Boot.model.Perfil;
import com.forohub.forohub_Spring_Boot.repository.PerfilRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {
    private final PerfilRepository perfilRepository;

    public PerfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }
    @PostMapping
    public ResponseEntity<Perfil> registrarPerfil(@RequestBody Perfil perfil) {
        Perfil nuevoPerfil = perfilRepository.save(perfil);
        return new ResponseEntity<>(nuevoPerfil, HttpStatus.CREATED);
    }
}
