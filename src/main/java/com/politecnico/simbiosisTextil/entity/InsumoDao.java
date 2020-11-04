package com.politecnico.simbiosisTextil.entity;

import com.politecnico.simbiosisTextil.entity.dao.Insumo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface InsumoDao extends CrudRepository<Insumo, Long> {
    @Transactional
    @Modifying
    @Query("update Insumo set cantidad=:cantidad where codigo=:codigo")
    int actualizarCantidadInsumos(@Param("codigo") long codigo, @Param("cantidad") int cantidad);

    @Query("select i from Insumo i where cantidad=0")
    Iterable<Insumo> buscarTodoAgotado();

    @Query("select i from Insumo i where i.codigo=:codigo and i.cantidad=0")
    Iterable<Insumo> buscarAgotadoPorCodigo(@Param("codigo") long codigo);
}
