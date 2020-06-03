package com.politecnico.simbiosisTextil.entity.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "PAGO")
public class Pago {
    @Id
    @Column(name = "Id_Pag", length = 30, nullable = false)
    private long id;
    @Column(name = "TipoPag", length = 7, nullable = false)
    private TipoPago tipoPago;
    @Column(name = "Cantidad_Pag", length = 30, nullable = false)
    private long cantidad;
    @Column(name = "Fecha_Pag", nullable = false)
    private LocalDate fecha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
