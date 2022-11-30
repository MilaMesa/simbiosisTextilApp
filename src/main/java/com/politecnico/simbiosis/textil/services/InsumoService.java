package com.politecnico.simbiosis.textil.services;

import com.politecnico.simbiosis.textil.controller.dto.InsumoDto;
import com.politecnico.simbiosis.textil.entity.UsuarioDao;
import com.politecnico.simbiosis.textil.entity.dao.Usuario;
import com.politecnico.simbiosis.textil.entity.InsumoDao;
import com.politecnico.simbiosis.textil.entity.dao.Insumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
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

    public List<InsumoDto> inventarioInsumo(long identificacion) {
        Iterable<Insumo> insumos;
        try {
            insumos = insumoDao.findByUserId(identificacion);
        } catch (NoResultException e) {
            insumos = new ArrayList<>();
        }
        return getInsumoDtos(insumos);
    }

    public List<InsumoDto> inventarioInsumo() {
        Iterable<Insumo> insumos;
        try {
            insumos = insumoDao.findAll();
        } catch (NoResultException e) {
            insumos = new ArrayList<>();
        }
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

