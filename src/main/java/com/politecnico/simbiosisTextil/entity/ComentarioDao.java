package com.politecnico.simbiosisTextil.entity;

import com.politecnico.simbiosisTextil.entity.dao.Comentario;
import org.springframework.data.repository.CrudRepository;

public interface ComentarioDao extends CrudRepository<Comentario, Long> {
}
