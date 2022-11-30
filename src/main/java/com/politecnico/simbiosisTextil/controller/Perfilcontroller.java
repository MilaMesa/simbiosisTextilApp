package com.politecnico.simbiosisTextil.controller;

import com.politecnico.simbiosisTextil.controller.dto.Perfil;
import com.politecnico.simbiosisTextil.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/perfil")
public class Perfilcontroller {
    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @GetMapping("/{id}")
    public Perfil getPerfilById(@PathVariable(value = "id") Long identificacion) {
        return usuarioService.getPerfil(identificacion);

    }

}
