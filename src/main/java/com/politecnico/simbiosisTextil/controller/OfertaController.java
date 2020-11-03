package com.politecnico.simbiosisTextil.controller;

import com.politecnico.simbiosisTextil.controller.dto.Publicacion;
import com.politecnico.simbiosisTextil.services.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/oferta")
public class OfertaController {
    @Autowired
    private OfertaService ofertaService;

    @GetMapping("/all")
    public List<Publicacion> getPublicaciones(){
        return ofertaService.getOfertas();
    }

    @PostMapping("/crear")
    public Publicacion crearPublicacion(@RequestBody Publicacion publicacion) {
        return ofertaService.crearOferta(publicacion);
    }

    @GetMapping("/{id}")
    public Publicacion obtenerOferta(@PathVariable(value = "id") long id) {
        return ofertaService.getOferta(id);
    }

}
