package com.politecnico.simbiosisTextil.services;

import com.politecnico.simbiosisTextil.controller.dto.Publicacion;
import com.politecnico.simbiosisTextil.entity.OfertaDao;
import com.politecnico.simbiosisTextil.entity.dao.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfertaService {
    @Autowired
    private OfertaDao ofertaDao;

    public List<Publicacion> getOfertas() {
        Iterable<Oferta> all = ofertaDao.findAll();
        List<Publicacion> list = new ArrayList<>();
        all.forEach(
                oferta -> {
                    list.add(crearPublicacion(oferta));
                }
        );
        return list;
    }

    private Publicacion crearPublicacion(Oferta oferta) {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(oferta.getId());
        publicacion.setDetalle(publicacion.getDetalle());
        if (null != oferta.getUsuario()) {
            publicacion.setNumeroIdentificacion(oferta.getUsuario().getNumeroIdentificacion());
            publicacion.setUsuario(oferta.getUsuario().getCuenta().getNombreUsuario());
        }
        return publicacion;
    }
}
