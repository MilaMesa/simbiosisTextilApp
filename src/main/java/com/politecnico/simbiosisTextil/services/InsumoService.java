package com.politecnico.simbiosisTextil.services;

import com.politecnico.simbiosisTextil.controller.dto.InsumoDto;
import com.politecnico.simbiosisTextil.entity.InsumoDao;
import com.politecnico.simbiosisTextil.entity.dao.Insumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsumoService {

    @Autowired
    private InsumoDao insumoDao;

    public void pedirInsumo(long codigo, int cantidad) {
        insumoDao.actualizarCantidadInsumos(codigo, cantidad);
    }

    public List<InsumoDto> inventarioInsumo() {
        Iterable<Insumo> insumos = insumoDao.findAll();

        return getInsumoDtos(insumos);
    }

    private List<InsumoDto> getInsumoDtos(Iterable<Insumo> insumos) {
        List<InsumoDto> inventario = new ArrayList<>();
        insumos.forEach(insumo -> {
            InsumoDto insumoDto = new InsumoDto();
            insumoDto.setCantidad(insumo.getCantidad());
            insumoDto.setCodigo(insumo.getCodigo());
            insumoDto.setColor(insumo.getColor());
            insumoDto.setDetalles(insumo.getDetalles());
            insumoDto.setForma(insumo.getForma());
            insumoDto.setMaterial(insumo.getMaterial());
            insumoDto.setReferenciaConfeccion(insumo.getReferenciaConfeccion());
            insumoDto.setTamaño(insumo.getTamaño());
            insumoDto.setValor(insumo.getValor());
            inventario.add(insumoDto);
        });
        return inventario;
    }

    public List<InsumoDto> insumosAgotados() {
        Iterable<Insumo> insumos = insumoDao.buscarTodoAgotado();
        return getInsumoDtos(insumos);
    }

    public List<InsumoDto> insumoAgotadoPorCodigo() {
        Iterable<Insumo> insumos = insumoDao.buscarAgotadoPorCodigo();
        return getInsumoDtos(insumos);
    }
}

