package com.politecnico.simbiosis.textil.services;


import com.politecnico.simbiosis.textil.entity.CuentaDao;
import com.politecnico.simbiosis.textil.entity.dao.Cuenta;
import com.politecnico.simbiosis.textil.entity.dao.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private CuentaDao repo;

    public void processOAuthPostLogin(String username) {
        if (!repo.findById(username).isPresent()) {
            Cuenta cuenta = new Cuenta();
            cuenta.setNombreUsuario(username);
            cuenta.setProvider(Provider.GOOGLE);
            repo.save(cuenta);
        }
    }

}