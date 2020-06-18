package com.politecnico.simbiosisTextil.entity.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @Column(name = "NumeroIdentificacion_Us", length = 11)
    private long numeroIdentificacion;
    @Column(name = "TipoIdentificacion_Us", nullable = false)
    private TipoIdentificacion tipoIdentificacion;
    @Column(name = "Nombre_Us", length = 40, nullable = false)
    private String nombre;
    @Column(name = "Apellido_Us", length = 40, nullable = false)
    private String apellido;
    @Column(name = "Telefono_Us", length = 7)
    private char[] telefono;
    @Column(name = "Celular_Us", length = 10, nullable = false)
    private String celular;
    @Column(name = "Direccion_Us", length = 50)
    private String direccion;
    @Column(name = "NombreEmpresa_Us", length = 25, nullable = false)
    private String nombreEmpresa;
    @Column(name = "TipoUsuario_Us", nullable = false)
    private TipoUsuario tipoUsuario;
    @Column(name = "Correo_Us", nullable = false, length = 30)
    private String correo;
    @OneToOne(mappedBy = "usuario")
    private Cuenta cuenta;
    @OneToMany(mappedBy = "usuario")
    private Set<Oferta> ofertas;
    @OneToMany(mappedBy = "usuario")
    private Set<Comentario> comentariosCreados;
    @OneToMany(mappedBy = "usuarioCreacion")
    private Set<Comentario> comentarios;

    public Long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public char[] getTelefono() {
        return telefono;
    }

    public void setTelefono(char[] telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void setNumeroIdentificacion(long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Set<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(Set<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
}
