package com.politecnico.simbiosis.textil.controller.dto;

import com.politecnico.simbiosis.textil.entity.dao.TipoOferta;

import java.time.LocalDate;

public class Publicacion {

    private long id;
    private LocalDate fecha;
    private TipoOferta tipoOferta;
    private String detalle;
    private long numeroIdentificacion;
    private String usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public TipoOferta getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(TipoOferta tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
