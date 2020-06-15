package com.politecnico.simbiosisTextil.entity.dao;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "OFERTA")
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Of", length = 30, nullable = false)
    private long id;
    @Column(name = "Fecha_Of", nullable = false)
    private LocalDate fecha;
    @Column(name = "Tipo_Of", nullable = false)
    private TipoOferta tipoOferta;
    @Column(name = "Detalle_Of", length = 900, nullable = false)
    private String detalle;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Usuario_Of", referencedColumnName = "NumeroIdentificacion_Us", nullable = false)
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
