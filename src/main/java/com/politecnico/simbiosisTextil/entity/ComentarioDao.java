package com.politecnico.simbiosisTextil.entity;

import com.politecnico.simbiosisTextil.controller.dto.ComentarioDto;
import com.politecnico.simbiosisTextil.entity.dao.Comentario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioDao extends CrudRepository<Comentario, Long> {
    @Query("select c from Comentario c where c.usuario.numeroIdentificacion = :numeroIdentificacion")
    List<Comentario> obtenerComentariosPorUsuario(@Param("numeroIdentificacion") long numeroIdentificacion);
}
