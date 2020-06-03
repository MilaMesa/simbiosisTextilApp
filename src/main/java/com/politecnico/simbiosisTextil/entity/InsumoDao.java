package com.politecnico.simbiosisTextil.entity;

import com.politecnico.simbiosisTextil.entity.dao.Insumo;
import org.springframework.data.repository.CrudRepository;

public interface InsumoDao extends CrudRepository<Insumo, Long> {
}
