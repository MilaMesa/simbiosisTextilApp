package com.politecnico.simbiosis.textil.services;

import com.politecnico.simbiosis.textil.controller.dto.Perfil;
import com.politecnico.simbiosis.textil.entity.UsuarioDao;
import com.politecnico.simbiosis.textil.entity.dao.Usuario;
import com.politecnico.simbiosis.textil.entity.dao.TipoUsuario;
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
            for (char c : telefono) {
                if (c != '\u0000') {
                    sbf.append(c);
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
