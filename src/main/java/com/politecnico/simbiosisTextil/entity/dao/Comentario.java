package com.politecnico.simbiosisTextil.entity.dao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "COMENTARIO")
public class Comentario {
    @Id
    @Column(name = "Id_Com", length = 30)
    private long id;
    @Column(name = "Mensaje_com", length = 900, nullable = false)
    private String mensaje;
    @Column(name = "Hora_Com", nullable = false)
    private LocalTime hora;
    @Column(name = "Fecha_Com", nullable = false)
    private LocalDate fecha;
    @Column(name = "Valoracion_Com", nullable = false)
    private int valoracion;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsuarioCreacion_Com", referencedColumnName = "NumeroIdentificacion_Us", nullable = false)
    private Usuario usuarioCreacion;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_Com", referencedColumnName = "NumeroIdentificacion_Us", nullable = false)
    private Usuario usuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
