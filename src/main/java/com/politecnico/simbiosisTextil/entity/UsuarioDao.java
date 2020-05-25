package com.politecnico.simbiosisTextil.entity;

import com.politecnico.simbiosisTextil.entity.dao.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {

}
