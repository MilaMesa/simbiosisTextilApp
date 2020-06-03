package com.politecnico.simbiosisTextil.entity.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "TRANSPORTE")
public class Transporte {
    @Id
    @Column(name = "Placa_Tra", length = 7, nullable = false)
    private String placa;
    @Column(name = "Carga_Tra", length = 30, nullable = false)
    private String carga;
    @Column(name = "Capacidad_Tra", length = 11, nullable = false)
    private int capacidad;
    @Column(name = "Origen_Tra", length = 30, nullable = false)
    private String origen;
    @Column(name = "Destino_Tra", length = 30, nullable = false)
    private String destino;
    @Column(name = "Fecha_Recogida_Tra")
    private LocalDate fechaRecogida;
    @Column(name = "Fecha_Entrega_Tra")
    private LocalDate fechaEntrega;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(LocalDate fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
