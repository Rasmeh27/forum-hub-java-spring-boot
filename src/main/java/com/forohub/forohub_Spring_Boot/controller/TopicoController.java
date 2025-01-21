package com.forohub.forohub_Spring_Boot.controller;
import com.forohub.forohub_Spring_Boot.model.Topico;
import com.forohub.forohub_Spring_Boot.model.dto.TopicoRequestDTO;
import com.forohub.forohub_Spring_Boot.model.dto.TopicoResponseDTO;
import com.forohub.forohub_Spring_Boot.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

     @PostMapping
    public ResponseEntity<Topico> registrarTopico(@Valid @RequestBody TopicoRequestDTO dto) {
        Topico topico = topicoService.crearTopico(dto);
        return new ResponseEntity<>(topico, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<TopicoResponseDTO>> listarTopicos() {
        List<TopicoResponseDTO> topicos = topicoService.listarTopicos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> detalleTopico(@PathVariable Long id) {
        TopicoResponseDTO topico = topicoService.obtenerDetalleTopico(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> actualizarTopico(
            @PathVariable Long id,
            @Valid @RequestBody TopicoRequestDTO dto) {
        TopicoResponseDTO topicoActualizado = topicoService.actualizarTopico(id, dto);
        return  ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
