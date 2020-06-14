package com.politecnico.simbiosisTextil.services;

import com.politecnico.simbiosisTextil.controller.dto.Perfil;
import com.politecnico.simbiosisTextil.entity.UsuarioDao;
import com.politecnico.simbiosisTextil.entity.dao.TipoUsuario;
import com.politecnico.simbiosisTextil.entity.dao.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioDao usuarioDao;

    public Perfil getPerfil(Long identificacion) {
        Optional<Usuario> usuarioOptional = usuarioDao.findById(identificacion);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Perfil perfil = new Perfil();
            perfil.setNumeroIdentificacion(usuario.getNumeroIdentificacion());
            perfil.setTipoIdentificacion(usuario.getTipoIdentificacion().name());
            if (TipoUsuario.TALLER.equals(usuario.getTipoUsuario())) {
                perfil.setNombre(String.format("%s %s", usuario.getNombre(), usuario.getApellido()));
            } else {
                perfil.setNombre(usuario.getNombreEmpresa());
            }
            char[] telefono = usuario.getTelefono();
            StringBuffer sbf = new StringBuffer();
            for (int i = 0; i < telefono.length; i++) {
                if (telefono[i] != '\u0000') {
                    sbf.append(telefono[i]);
                }
            }
            perfil.setTelefono(sbf.toString().trim());
            perfil.setCelular(usuario.getCelular());
            perfil.setCorreo(usuario.getCorreo());
            perfil.setDireccion(usuario.getDireccion());
            return perfil;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No se obtuvo usuario con la identificacion %s", identificacion));
        }
    }

}
