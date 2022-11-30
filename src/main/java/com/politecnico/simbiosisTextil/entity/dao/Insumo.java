package com.politecnico.simbiosisTextil.entity.dao;

import javax.persistence.*;

@Entity
@Table(name = "INSUMO")
public class Insumo {
    @Id
    @Column(name = "Codigo_Ins", length = 10, nullable = false)
    private long codigo;
    @Column(name = "Material_Ins", length = 100, nullable = false)
    private String material;
    @Column(name = "Tamaño_Ins", length = 7, nullable = false)
    private float tamaño;
    @Column(name = "Color_Ins", length = 50, nullable = false)
    private String color;
    @Column(name = "Forma_Ins", length = 50, nullable = false)
    private String forma;
    @Column(name = "Cantidad_Ins", length = 7, nullable = false)
    private int cantidad;
    @Column(name = "Valor_Ins", length = 18, nullable = false)
    private float valor;
    @Column(name = "ReferenciaConfeccion_Ins", length = 20, nullable = false)
    private String referenciaConfeccion;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroIdentificacion_Us_Ins", referencedColumnName = "NumeroIdentificacion_Us", nullable = false)
    private Usuario usuario;
    @Column(name = "Detalles_Ins", length = 900, nullable = false)
    private String detalles;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getTamaño() {
        return tamaño;
    }

    public void setTamaño(float tamaño) {
        this.tamaño = tamaño;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getReferenciaConfeccion() {
        return referenciaConfeccion;
    }

    public void setReferenciaConfeccion(String referenciaConfeccion) {
        this.referenciaConfeccion = referenciaConfeccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
}
