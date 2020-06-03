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

}
