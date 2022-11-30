package com.politecnico.simbiosis.textil.entity;

import com.politecnico.simbiosis.textil.entity.dao.Comentario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioDao extends CrudRepository<Comentario, Long> {
    @Query("select c from Comentario c where c.usuario.numeroIdentificacion = :numeroIdentificacion")
    List<Comentario> obtenerComentariosPorUsuario(@Param("numeroIdentificacion") long numeroIdentificacion);
}
