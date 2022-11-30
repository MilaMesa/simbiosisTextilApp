package com.politecnico.simbiosis.textil.services;

import com.mysql.cj.util.StringUtils;
import com.politecnico.simbiosis.textil.controller.dto.ComentarioDto;
import com.politecnico.simbiosis.textil.controller.dto.Valoracion;
import com.politecnico.simbiosis.textil.entity.ComentarioDao;
import com.politecnico.simbiosis.textil.entity.UsuarioDao;
import com.politecnico.simbiosis.textil.entity.dao.Comentario;
import com.politecnico.simbiosis.textil.entity.dao.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioService {
    @Autowired
    ComentarioDao comentarioDao;
    @Autowired
    UsuarioDao usuarioDao;

    public List<ComentarioDto> obtenerComentariosPorUsuario(long numeroIdentificacion) {
        List<Comentario> comentarios = comentarioDao.obtenerComentariosPorUsuario(numeroIdentificacion);
        return comentarios.stream().map(comentario -> obtenerComentarioDto(comentario)).collect(Collectors.toList());
    }

    private ComentarioDto obtenerComentarioDto(Comentario comentario) {
        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setId(comentario.getId());
        comentarioDto.setFecha(LocalDateTime.of(comentario.getFecha(), comentario.getHora()));
        comentarioDto.setMensaje(comentario.getMensaje());
        comentarioDto.setValoracion(comentario.getValoracion());
        comentarioDto.setUsuario(comentario.getUsuario().getNumeroIdentificacion());
        comentarioDto.setUsuarioCreacion(comentario.getUsuarioCreacion().getNumeroIdentificacion());
        comentarioDto.setNombreUsuario(comentario.getUsuario().getNombre());
        comentarioDto.setNombreUsuarioCreacion(comentario.getUsuarioCreacion().getNombre());
        return comentarioDto;
    }

    public ComentarioDto crearComentario(ComentarioDto comentarioDto) {
        validarComentario(comentarioDto);
        Comentario comentario = new Comentario();
        comentario.setFecha(comentarioDto.getFecha().toLocalDate());
        comentario.setHora(LocalTime.from(comentarioDto.getFecha()));
        comentario.setMensaje(comentarioDto.getMensaje());
        Optional<Usuario> usuarioOptional = usuarioDao.findById(comentarioDto.getUsuario());
        if (usuarioOptional.isPresent()) {
            comentario.setUsuario(usuarioOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "No existe el usuario al que se le esta agregando el comentario");
        }
        Optional<Usuario> usuarioCreacionOptional = usuarioDao.findById(comentarioDto.getUsuarioCreacion());
        if (usuarioCreacionOptional.isPresent()) {
            comentario.setUsuarioCreacion(usuarioCreacionOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "No existe el usuario que esta creando el comentario");
        }
        Comentario comentarioSave = comentarioDao.save(comentario);
        comentarioDto.setId(comentarioSave.getId());
        return comentarioDto;
    }

    private void validarComentario(ComentarioDto comentarioDto) {
        if (null == comentarioDto) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio la informacion para crear el comentario");
        }
        if (StringUtils.isNullOrEmpty(comentarioDto.getMensaje())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el mensaje para crear el comentario");
        }
        if (null == comentarioDto.getFecha()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio la fecha para crear el comentario");
        }
        if (0 == comentarioDto.getUsuario()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el usuario para crearle el comentario");
        }
        if (0 == comentarioDto.getUsuarioCreacion()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el usuario para crear el comentario");
        }
    }

    public boolean valorarComentario(Valoracion valoracion) {
        validarValoracion(valoracion);
        Optional<Comentario> comentarioOptional = comentarioDao.findById(valoracion.getId());
        if (comentarioOptional.isPresent()) {
            Comentario comentario = comentarioOptional.get();
            comentario.setValoracion((comentario.getValoracion() + valoracion.getValoracion()) / 2);
            Comentario save = comentarioDao.save(comentario);
            return 0 < save.getValoracion();
        }
        throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "no se encontro el comentario que desea valorar");
    }

    private void validarValoracion(Valoracion valoracion) {
        if (null == valoracion) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio la informacion para valorar el comentario");
        }
        if (0 == valoracion.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el id del comentario para agragar la valoracion");
        }
        if (0 == valoracion.getValoracion()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio la valoracion para el comentario");
        }
    }
}
