package com.politecnico.simbiosisTextil.controller;

import com.politecnico.simbiosisTextil.controller.dto.Registro;
import com.politecnico.simbiosisTextil.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/")
    public Registro iniciarSeccion(@RequestBody Registro registro) {
        return cuentaService.validarLogin(registro);
    }
}
