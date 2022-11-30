package com.politecnico.simbiosisTextil.controller;

import com.politecnico.simbiosisTextil.controller.dto.Registro;
import com.politecnico.simbiosisTextil.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/crear")
    public Registro crearCuentaUsuario(@RequestBody Registro registro){
        return cuentaService.crearCuenta(registro);
    }

    @PostMapping("/actualizar")
    public Registro actualizarCuentaUsuario(@RequestBody Registro registro) {
        return cuentaService.actualizarCuenta(registro);
    }
}
