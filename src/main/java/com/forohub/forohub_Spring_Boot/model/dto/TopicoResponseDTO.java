package com.forohub.forohub_Spring_Boot.model.dto;

import java.util.Date;

public class TopicoResponseDTO {
    private String titulo;
    private String mensaje;
    private Date fechaCreacion;
    private String estado;
    private String autor;
    private String curso;

    public TopicoResponseDTO(String titulo,
                             String mensaje,
                             Date fechaCreacion,
                             String estado,
                             String autor,
                             String curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.autor = autor;
        this.curso = curso;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
