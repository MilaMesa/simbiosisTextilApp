package com.politecnico.simbiosisTextil.entity;

import com.politecnico.simbiosisTextil.entity.dao.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface CuentaDao extends CrudRepository<Cuenta, String> {
}
