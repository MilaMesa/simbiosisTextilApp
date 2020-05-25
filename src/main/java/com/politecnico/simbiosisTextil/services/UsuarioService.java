package com.politecnico.simbiosisTextil.services;

import com.politecnico.simbiosisTextil.controller.dto.Perfil;
import com.politecnico.simbiosisTextil.entity.UsuarioDao;
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
            perfil.setNombre(String.format("%s %s", usuario.getNombres(), usuario.getApellidos()));
            return perfil;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No se obtuvo usuario con la identificacion %s", identificacion));
        }
    }

}
