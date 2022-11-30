package com.politecnico.simbiosis.textil.entity;

import com.politecnico.simbiosis.textil.entity.dao.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface CuentaDao extends CrudRepository<Cuenta, String> {
}
