package com.politecnico.simbiosis.textil.services;

import com.mysql.cj.util.StringUtils;
import com.politecnico.simbiosis.textil.controller.dto.Registro;
import com.politecnico.simbiosis.textil.entity.CuentaDao;
import com.politecnico.simbiosis.textil.entity.UsuarioDao;
import com.politecnico.simbiosis.textil.entity.dao.Cuenta;
import com.politecnico.simbiosis.textil.entity.dao.TipoIdentificacion;
import com.politecnico.simbiosis.textil.entity.dao.TipoUsuario;
import com.politecnico.simbiosis.textil.entity.dao.Usuario;
import com.politecnico.simbiosis.textil.security.JwtCreator;
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
                Cuenta cuenta = cuentaUsuario.get();
                if (cuenta.getPassword().equals(registro.getPassword())) {
                    registro.setNumeroIdentificacion(cuenta.getUsuario().getNumeroIdentificacion());
                    registro.setError(false);
                    registro.setToken(JwtCreator.getJWTToken(cuenta.getNombreUsuario()));
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
        registro.setToken(JwtCreator.getJWTToken(cuentaUsuario.getNombreUsuario()));
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

    public Registro actualizarCuenta(Registro registro) {
        validarDatosObligatoriosActualizacion(registro);
        Optional<Cuenta> cuentaOptional = cuentaDao.findById(registro.getUsuario());
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            if (registro.getPassword().equals(cuenta.getPassword())) {
                if (!StringUtils.isNullOrEmpty(registro.getNewPassword())) {
                    cuenta.setPassword(registro.getNewPassword());
                }
                Usuario usuario = cuenta.getUsuario();
                if (TipoUsuario.TALLER.equals(usuario.getTipoUsuario())) {
                    String[] nombrecompleto = registro.getNombre().split(" ");
                    if (2 == nombrecompleto.length) {
                        usuario.setNombre(nombrecompleto[0]);
                        usuario.setApellido(nombrecompleto[1]);
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre completo del usuario solo debe estar compuesto por 2 palabras");
                    }
                } else {
                    usuario.setNombreEmpresa(registro.getNombre());
                }
                usuario.setCorreo(registro.getCorreo());
                char[] nuevoTelefono = new char[7];
                registro.getTelefono().getChars(0, registro.getTelefono().length(), nuevoTelefono, 0);
                usuario.setTelefono(nuevoTelefono);
                usuario.setCelular(registro.getCelular());
                usuario.setCorreo(registro.getCorreo());
                usuario.setDireccion(registro.getDireccion());
                cuenta.setUsuario(usuario);
                cuentaDao.save(cuenta);
                return registro;
            }
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "La contraseña no correponde a la contraseña actual");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro la cuenta para la que se desean actualizar los datos");
    }

    private void validarDatosObligatoriosActualizacion(Registro registro) {
        if (null == registro) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio la informacion para el registro.");
        }
        if (!existeUsuario(registro.getNumeroIdentificacion())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No existe el usuario que intenta actualizar");
        }
        if (StringUtils.isNullOrEmpty(registro.getUsuario())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el usuario para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el nombre para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getCelular())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el celular para el registro.");
        }
        if (StringUtils.isNullOrEmpty(registro.getCorreo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se recibio el correo para el registro.");
        }
    }
}
