package com.politecnico.simbiosisTextil.controller;

import com.politecnico.simbiosisTextil.controller.dto.Publicacion;
import com.politecnico.simbiosisTextil.services.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/oferta")
public class OfertaController {
    @Autowired
    private OfertaService ofertaService;

    @GetMapping("all")
    public List<Publicacion> getPublicaciones(){
        return ofertaService.getOfertas();
    }
}
