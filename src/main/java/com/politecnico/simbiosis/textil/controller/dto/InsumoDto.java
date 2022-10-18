package com.politecnico.simbiosis.textil.controller.dto;

public class InsumoDto {
    private long codigo;
    private String material;
    private float tamaño;
    private String color;
    private String forma;
    private int cantidad;
    private float valor;
    private String referenciaConfeccion;
    private String detalles;
    private long identificacionUsuario;

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

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public long getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(long identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }
}
