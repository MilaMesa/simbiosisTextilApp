package com.politecnico.simbiosisTextil.services;

import com.politecnico.simbiosisTextil.controller.dto.Registro;
import com.politecnico.simbiosisTextil.entity.CuentaDao;
import com.politecnico.simbiosisTextil.entity.UsuarioDao;
import com.politecnico.simbiosisTextil.entity.dao.Cuenta;
import com.politecnico.simbiosisTextil.entity.dao.TipoIdentificacion;
import com.politecnico.simbiosisTextil.entity.dao.TipoUsuario;
import com.politecnico.simbiosisTextil.entity.dao.Usuario;
import javafx.util.converter.CharacterStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CuentaService {
    @Autowired
    private CuentaDao cuentaDao;
    @Autowired
    private UsuarioDao usuarioDao;

    public Registro validarLogin(Registro registro) {
        if (null == registro.getUsuario() || null == registro.getPassword()) {
            registro.setError(true);
        } else {
            Optional<Cuenta> cuentaUsuario = cuentaDao.findById(registro.getUsuario());
            if (cuentaUsuario.isPresent()) {
                com.politecnico.simbiosisTextil.entity.dao.Cuenta password = cuentaUsuario.get();
                if (password.getPassword().equals(registro.getPassword())) {
                    registro.setError(false);
                } else {
                    registro.setError(true);
                }
            } else {
                registro.setError(true);
            }
        }
        return registro;
    }

    public Registro crearCuenta(Registro registro) {
        Cuenta cuentaUsuario = validarYObtenerDatosDelRegistro(registro);
        cuentaDao.save(cuentaUsuario);
        registro.setError(false);
        return registro;
    }

    private Cuenta validarYObtenerDatosDelRegistro(Registro registro) {
        Cuenta cuenta = new Cuenta();
        Usuario usuario = new Usuario();
        if(!existeUsuario(registro.getUsuario())){
            cuenta.setNombreUsuario(registro.getUsuario());
            cuenta.setPassword(registro.getPassword());
            usuario.setNombre(registro.getNombre());
            usuario.setApellido(registro.getApellido());
            char[] telefono = new char[7];
            registro.getTelefono().getChars(0, registro.getTelefono().length(), telefono, 0);
            usuario.setTelefono(telefono);
            usuario.setCelular(registro.getCelular());
            usuario.setCorreo(registro.getCorreo());
            usuario.setDireccion(registro.getDireccion());
            usuario.setNombreEmpresa(registro.getNombreEmpresa());
            usuario.setTipoUsuario(TipoUsuario.valueOf(registro.getTipoUsuario()));
            usuario.setTipoIdentificacion(TipoIdentificacion.valueOf(registro.getTipoIdentificacion()));
            usuario.setNumeroIdentificacion(registro.getNumeroIdentificacion());
            usuarioDao.save(usuario);
        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"El nombre de usuario ya existe en el sistema.");
        }
        cuenta.setUsuario(usuario);
        return cuenta;
    }

    private boolean existeUsuario(String usuario) {
        return cuentaDao.findById(usuario).isPresent();
    }
}
