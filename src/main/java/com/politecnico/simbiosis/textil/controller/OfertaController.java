package com.politecnico.simbiosis.textil.controller;

import com.politecnico.simbiosis.textil.services.OfertaService;
import com.politecnico.simbiosis.textil.controller.dto.Publicacion;
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

    @PostMapping("/eliminar/{id}")
    public void eliminarOferta(@PathVariable(value = "id") long id) {
        ofertaService.eliminarOferta(id);
    }

}
