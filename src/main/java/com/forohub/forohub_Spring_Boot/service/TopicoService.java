package com.forohub.forohub_Spring_Boot.service;

import com.forohub.forohub_Spring_Boot.model.Curso;
import com.forohub.forohub_Spring_Boot.model.StatusTopico;
import com.forohub.forohub_Spring_Boot.model.Topico;
import com.forohub.forohub_Spring_Boot.model.Usuario;
import com.forohub.forohub_Spring_Boot.model.dto.TopicoRequestDTO;
import com.forohub.forohub_Spring_Boot.model.dto.TopicoResponseDTO;
import com.forohub.forohub_Spring_Boot.repository.CursoRepository;
import com.forohub.forohub_Spring_Boot.repository.TopicoRepository;
import com.forohub.forohub_Spring_Boot.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//Encapsulando la toda la logica de negicio en una sola clase Servicio.
@Service
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Topico crearTopico(TopicoRequestDTO dto) {
        Topico topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensaje(dto.getMensaje());
        topico.setFechaCreacion(new Date());
        topico.setStatus(StatusTopico.NO_RESPONDIDO);
        topico.setAutor(usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("El autor no existe.")));
        topico.setCurso(cursoRepository.findById(dto.getCursoId())
                .orElseThrow(()->new IllegalArgumentException("El curso no existe")));

        return topicoRepository.save(topico);
    }

    //Este metodo sirve para obtener los datos de los topicos y mapearlos a TopicResponseDTO.
    public List<TopicoResponseDTO> listarTopicos() {
        return topicoRepository.findByActivoTrue()
                .stream()
                .map(topico -> new TopicoResponseDTO(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus() != null ? topico.getStatus().toString() : "NO_DEFINIDO",
                        topico.getAutor().getNombre(),
                        topico.getCurso().getNombre()
                ))
                .collect(Collectors.toList());
    }

    public TopicoResponseDTO obtenerDetalleTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("El topico con ID: " + id + " no existe."));
        return new TopicoResponseDTO(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus() != null ? topico.getStatus().toString() : "NO_DEFINIDO",
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }

    public TopicoResponseDTO actualizarTopico(Long id, @Valid TopicoRequestDTO dto) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("El topico con ID " + id + " No existe."));

        //Esta parte nos sirve para actualizar los campos del topico
        topico.setTitulo(dto.getTitulo());
        topico.setMensaje(dto.getMensaje());

        Usuario autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(()->new IllegalArgumentException("El autor con ID " + dto.getAutorId() + " no existe."));
        topico.setAutor(autor);

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(()-> new IllegalArgumentException("El curso con ID " + dto.getCursoId() + " no existe."));
        topico.setCurso(curso);

        //Guardar cambios en la base de datos.
        Topico topicoActualizado = topicoRepository.save(topico);

        return new TopicoResponseDTO(
                topicoActualizado.getTitulo(),
                topicoActualizado.getMensaje(),
                topicoActualizado.getFechaCreacion(),
                topicoActualizado.getStatus()   != null ? topicoActualizado.getStatus().toString() : "NO_DEFINIDO",
                topicoActualizado.getAutor().getNombre(),
                topicoActualizado.getCurso().getNombre()
        );
    }

    //Este metodo en vez de eliminar completamente el topico, se marca como oculto y se desactiva en la base de datos.
    public void eliminarTopico(Long id) {
       Topico topico = topicoRepository.findById(id)
               .orElseThrow(()-> new IllegalArgumentException("El topico ID " +id+ " no existe."));
       topico.setActivo(false);
       topicoRepository.save(topico);
    }
}
