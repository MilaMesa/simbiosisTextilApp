package com.politecnico.simbiosisTextil.entity;

import com.politecnico.simbiosisTextil.entity.dao.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.correo = :email")
    Optional<Usuario> findByEmail(@Param("email") String correo);
}
