package com.politecnico.simbiosis.textil.controller;

import com.politecnico.simbiosis.textil.controller.dto.InsumoDto;
import com.politecnico.simbiosis.textil.services.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/insumo")
public class InsumosController {
    @Autowired
    private InsumoService insumoService;

    @GetMapping("/all")
    public List<InsumoDto> obtenetTodosLosInsumos() {
        return insumoService.inventarioInsumo();
    }

    @GetMapping("/all/{identificacion}")
    public List<InsumoDto> obtenetTodosLosInsumosDelUsuario(@PathVariable(value = "identificacion") long identificacion) {
        return insumoService.inventarioInsumo(identificacion);
    }

    @GetMapping("/agotado")
    public List<InsumoDto> obtenerInsumosAgotados() {
        return insumoService.insumosAgotados();
    }

    @GetMapping("/agotado/{codigo}")
    public List<InsumoDto> obtenerInsumoPorCodigo(@PathVariable(value = "codigo") long codigo) {
        return insumoService.insumoAgotadoPorCodigo(codigo);
    }

    @PostMapping("/{codigo}/actualizar/{cantidad}")
    public int actualizarInsumo(@PathVariable(value = "codigo") long codigo, @PathVariable(value = "cantidad") int cantidad) {
        return insumoService.pedirInsumo(codigo, cantidad);
    }

    @PostMapping("/crear")
    public InsumoDto crearInsumo(@RequestBody InsumoDto insumoDto) {
        return insumoService.crearInsumo(insumoDto);
    }
}
