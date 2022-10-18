package com.politecnico.simbiosis.textil.services;

import com.mysql.cj.util.StringUtils;
import com.politecnico.simbiosis.textil.entity.dao.Usuario;
import com.politecnico.simbiosis.textil.controller.dto.Publicacion;
import com.politecnico.simbiosis.textil.entity.OfertaDao;
import com.politecnico.simbiosis.textil.entity.UsuarioDao;
import com.politecnico.simbiosis.textil.entity.dao.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfertaService {
    @Autowired
    private OfertaDao ofertaDao;
    @Autowired
    private UsuarioDao usuarioDao;

    public List<Publicacion> getOfertas() {
        Iterable<Oferta> all = ofertaDao.findAll();
        List<Publicacion> list = new ArrayList<>();
        all.forEach(
                oferta -> {
                    list.add(obtenerPublicacion(oferta));
                }
        );
        return list;
    }

    private Publicacion obtenerPublicacion(Oferta oferta) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(oferta.getId());
        publicacion.setDetalle(oferta.getDetalle());
        publicacion.setFecha(oferta.getFecha());
        publicacion.setTipoOferta(oferta.getTipoOferta());
        if (null != oferta.getUsuario()) {
            publicacion.setNumeroIdentificacion(oferta.getUsuario().getNumeroIdentificacion());
            publicacion.setUsuario(oferta.getUsuario().getCuenta().getNombreUsuario());
        }
        return publicacion;
    }

    public Publicacion crearOferta(Publicacion publicacion) {
        validarPublicacion(publicacion);
        Oferta oferta = new Oferta();
        oferta.setDetalle(publicacion.getDetalle());
        oferta.setFecha(publicacion.getFecha());
        oferta.setTipoOferta(publicacion.getTipoOferta());
        Optional<Usuario> usuarioOptional = usuarioDao.findById(publicacion.getNumeroIdentificacion());
        if (usuarioOptional.isPresent()) {
            oferta.setUsuario(usuarioOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Ocurrio un error obteneindo el usuario para crear la publicacion");
        }
        Oferta ofertaGuardada = ofertaDao.save(oferta);
        publicacion.setId(ofertaGuardada.getId());
        return publicacion;
    }

    private void validarPublicacion(Publicacion publicacion) {
        if (null == publicacion) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No llego la informacion necesaria para crear la publicación");
        }
        if (StringUtils.isNullOrEmpty(publicacion.getDetalle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No llego el detalle para crear la publicación");
        }
        if (null == publicacion.getFecha()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No llego la fecha para crear la publicación");
        }
        if (0L == publicacion.getNumeroIdentificacion()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No llego el numero de identificacion del usuario");
        }
        if (!usuarioDao.existsById(publicacion.getNumeroIdentificacion())) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "El numero de identificacion no corresponde a ningun usuario registrado.");
        }
    }

    public Publicacion getOferta(long id) {
        Optional<Oferta> ofertaOptional = ofertaDao.findById(id);
        if (ofertaOptional.isPresent()) {
            Oferta oferta = ofertaOptional.get();
            Publicacion publicacion = new Publicacion();
            publicacion.setDetalle(oferta.getDetalle());
            publicacion.setTipoOferta(oferta.getTipoOferta());
            publicacion.setFecha(oferta.getFecha());
            publicacion.setNumeroIdentificacion(oferta.getUsuario().getNumeroIdentificacion());
            publicacion.setUsuario(oferta.getUsuario().getCuenta().getNombreUsuario());
            return publicacion;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro la oferta Que esta buscando");
    }

    public void eliminarOferta(long id) {
        ofertaDao.deleteById(id);
    }
}
