package com.politecnico.simbiosisTextil.controller;

import com.politecnico.simbiosisTextil.controller.dto.ComentarioDto;
import com.politecnico.simbiosisTextil.controller.dto.Valoracion;
import com.politecnico.simbiosisTextil.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/{numeroIdentificacion}")
    public List<ComentarioDto> obtenerComentariosPorUsuario(@PathVariable(value = "numeroIdentificacion") long numeroIdentificacion) {
        return comentarioService.obtenerComentariosPorUsuario(numeroIdentificacion);
    }

    @PostMapping("/crear")
    public ComentarioDto crearComentario(@RequestBody ComentarioDto comentarioDto) {
        return comentarioService.crearComentario(comentarioDto);
    }

    @PostMapping("/valorar")
    public boolean valorarComentario(@RequestBody Valoracion valoracion) {
        return comentarioService.valorarComentario(valoracion);
    }
}
