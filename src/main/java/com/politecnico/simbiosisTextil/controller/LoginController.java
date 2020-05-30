package com.politecnico.simbiosisTextil.controller;

import com.politecnico.simbiosisTextil.controller.dto.Cuenta;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/")
    public Cuenta iniciarSeccion(@RequestBody Cuenta cuenta) {
        System.out.println("Usuario: " + cuenta.getUsuario() + " Contraseña: " + cuenta.getPassword());
        cuenta.setError(true);
        return cuenta;
    }
}
