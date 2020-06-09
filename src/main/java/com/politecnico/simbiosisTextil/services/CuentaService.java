package com.politecnico.simbiosisTextil.services;

import com.mysql.cj.util.StringUtils;
import com.politecnico.simbiosisTextil.controller.dto.Registro;
import com.politecnico.simbiosisTextil.entity.CuentaDao;
import com.politecnico.simbiosisTextil.entity.UsuarioDao;
import com.politecnico.simbiosisTextil.entity.dao.Cuenta;
import com.politecnico.simbiosisTextil.entity.dao.TipoIdentificacion;
import com.politecnico.simbiosisTextil.entity.dao.TipoUsuario;
import com.politecnico.simbiosisTextil.entity.dao.Usuario;
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
        validarDatosDelRegistro(registro);
        Cuenta cuenta = new Cuenta();
        Usuario usuario = new Usuario();
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
        cuenta.setUsuario(usuario);
        return cuenta;
    }

    private void validarDatosDelRegistro(Registro registro) {
        validarDatosObligatorios(registro);
        if (existeNombreUsuario(registro.getUsuario())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El nombre de usuario ya existe en el sistema.");
        }
        if (existeUsuario(registro.getNumeroIdentificacion())) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "La identificacion ingresada ya existe para otra cuenta.");
        }
        if (existeCorreoElectronico(registro.getCorreo())) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "El correo ingresado ya existe para otra cuenta.");
        }
    }

    private void validarDatosObligatorios(Registro registro) {
        if (null == registro) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio la informacion para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getCorreo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el correo para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getApellido())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el apellido para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getCelular())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el celular para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el nombre para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio la contraseña para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getTipoIdentificacion())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el tipo de identificacion para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getTipoUsuario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el tipo de usuario para el registro.");
        }
    }

    private boolean existeCorreoElectronico(String correo) {
        return usuarioDao.findByEmail(correo).isPresent();
    }


    private boolean existeUsuario(long numeroIdentificacion) {
        return usuarioDao.findById(numeroIdentificacion).isPresent();
    }

    private boolean existeNombreUsuario(String usuario) {
        return cuentaDao.findById(usuario).isPresent();
    }
}
