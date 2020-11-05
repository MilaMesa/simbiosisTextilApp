package com.politecnico.simbiosisTextil.services;

import com.politecnico.simbiosisTextil.controller.dto.InsumoDto;
import com.politecnico.simbiosisTextil.entity.InsumoDao;
import com.politecnico.simbiosisTextil.entity.UsuarioDao;
import com.politecnico.simbiosisTextil.entity.dao.Insumo;
import com.politecnico.simbiosisTextil.entity.dao.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InsumoService {

    @Autowired
    private InsumoDao insumoDao;
    @Autowired
    private UsuarioDao usuarioDao;

    public InsumoDto crearInsumo(InsumoDto insumoDto) {
        Insumo insumo = new Insumo();
        insumo.setCantidad(insumoDto.getCantidad());
        insumo.setCodigo(insumoDto.getCodigo());
        insumo.setColor(insumoDto.getColor());
        insumo.setValor(insumoDto.getValor());
        insumo.setTamaño(insumoDto.getTamaño());
        insumo.setReferenciaConfeccion(insumoDto.getReferenciaConfeccion());
        insumo.setMaterial(insumoDto.getMaterial());
        insumo.setForma(insumoDto.getForma());
        insumo.setDetalles(insumoDto.getDetalles());
        long identificacionUsuario = insumoDto.getIdentificacionUsuario();
        Optional<Usuario> usuario = usuarioDao.findById(identificacionUsuario);
        if (usuario.isPresent()) {
            insumo.setUsuario(usuario.get());
        } else {
            insumo.setUsuario(new Usuario());
        }
        insumoDao.save(insumo);
        return insumoDto;
    }

    public int pedirInsumo(long codigo, int cantidad) {
        return insumoDao.actualizarCantidadInsumos(codigo, cantidad);
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

    public List<InsumoDto> insumoAgotadoPorCodigo(long codigo) {
        Iterable<Insumo> insumos = insumoDao.buscarAgotadoPorCodigo(codigo);
        return getInsumoDtos(insumos);
    }
}

