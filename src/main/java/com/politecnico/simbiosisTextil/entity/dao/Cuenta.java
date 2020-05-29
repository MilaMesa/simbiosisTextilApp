package com.politecnico.simbiosisTextil.entity.dao;

import javax.persistence.*;

@Entity
@Table(name="CUENTA")
public class Cuenta {
    @Id
    @Column(name="Usuario_Cu", length = 15)
    private String nombre_usuario;
    @Column(name="Password_Cu", length = 30, nullable= false)
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroIdentificacion_Us", nullable= false)
    private Usuario usuario;

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
