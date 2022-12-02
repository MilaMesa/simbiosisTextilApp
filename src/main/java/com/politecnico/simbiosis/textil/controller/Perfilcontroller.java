package com.politecnico.simbiosis.textil.controller;

import com.politecnico.simbiosis.textil.controller.dto.Perfil;
import com.politecnico.simbiosis.textil.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/perfil")
public class Perfilcontroller {
    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public Perfil getPerfilById(@PathVariable(value = "id") Long identificacion) {
        return usuarioService.getPerfil(identificacion);

    }

}
