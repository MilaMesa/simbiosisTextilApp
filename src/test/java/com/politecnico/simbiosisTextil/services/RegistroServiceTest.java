package com.politecnico.simbiosisTextil.services;

import com.politecnico.simbiosisTextil.controller.dto.Registro;
import com.politecnico.simbiosisTextil.entity.CuentaDao;
import com.politecnico.simbiosisTextil.entity.dao.Cuenta;
import com.politecnico.simbiosisTextil.entity.dao.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class RegistroServiceTest {
    @InjectMocks
    private CuentaService cuentaService;

    @Mock
    private CuentaDao cuentaDao;

    //Test unitario
    @Test
    void validarLoginExitoso() {
        //Arrange - iniciar o preparar el test
        Cuenta cuentaBd = new Cuenta();
        cuentaBd.setNombreUsuario("usuario2");
        cuentaBd.setPassword("123");
        cuentaBd.setUsuario(new Usuario());
        Mockito.when(cuentaDao.findById("usuario2")).thenReturn(Optional.of(cuentaBd));
        Registro registroFront = new Registro();
        registroFront.setUsuario("usuario2");
        registroFront.setPassword("123");

        //Action ejecutar la prueba
        Registro registroValida = cuentaService.validarLogin(registroFront);

        //Assert validar que el resultado es el esperado
        Assertions.assertFalse(registroValida.isError(), "El login no fue exitoso");
    }
}