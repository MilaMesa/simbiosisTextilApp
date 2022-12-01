package com.politecnico.simbiosis.textil.entity.dao;

import javax.persistence.*;

@Entity
@Table(name = "CUENTA")
public class Cuenta {
    @Id
    @Column(name = "Usuario_Cu", length = 15)
    private String nombreUsuario;
    @Column(name = "Password_Cu", length = 30, nullable = false)
    private String password;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "NumeroIdentificacion_Cu", referencedColumnName = "NumeroIdentificacion_Us", nullable = false)
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private Provider provider;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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
