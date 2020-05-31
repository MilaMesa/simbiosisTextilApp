package com.politecnico.simbiosisTextil.services;

import com.politecnico.simbiosisTextil.controller.dto.Cuenta;
import com.politecnico.simbiosisTextil.entity.CuentaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaService {
    @Autowired
    private CuentaDao cuentaDao;

    public Cuenta validarLogin(Cuenta cuenta) {
        if (null == cuenta.getUsuario() || null == cuenta.getPassword()) {
            cuenta.setError(true);
        } else {
            Optional<com.politecnico.simbiosisTextil.entity.dao.Cuenta> cuentaUsuario = cuentaDao.findById(cuenta.getUsuario());
            if (cuentaUsuario.isPresent()) {
                com.politecnico.simbiosisTextil.entity.dao.Cuenta password = cuentaUsuario.get();
                if (password.getPassword().equals(cuenta.getPassword())) {
                    cuenta.setError(false);
                } else {
                    cuenta.setError(true);
                }
            } else {
                cuenta.setError(true);
            }
        }
        return cuenta;
    }
}
