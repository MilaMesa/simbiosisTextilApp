package com.politecnico.simbiosisTextil.entity;

import com.politecnico.simbiosisTextil.entity.dao.Insumo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InsumoDao extends CrudRepository<Insumo, Long> {
    @Query("update Insumo set cantidad=:cantidad where codigo=:codigo")
    Insumo actualizarCantidadInsumos(@Param("codigo")  long codigo,@Param("cantidad") int cantidad);

    Iterable<Insumo> buscarTodoAgotado();

    Iterable<Insumo> buscarAgotadoPorCodigo();
}
