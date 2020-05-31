package com.politecnico.simbiosisTextil.services;

import com.politecnico.simbiosisTextil.entity.CuentaDao;
import com.politecnico.simbiosisTextil.entity.dao.Cuenta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest
class CuentaServiceTest {
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
        Mockito.when(cuentaDao.findById("usuario2")).thenReturn(Optional.of(cuentaBd));
        com.politecnico.simbiosisTextil.controller.dto.Cuenta cuentaFront = new com.politecnico.simbiosisTextil.controller.dto.Cuenta();
        cuentaFront.setUsuario("usuario2");
        cuentaFront.setPassword("123");

        //Action ejecutar la prueba
        com.politecnico.simbiosisTextil.controller.dto.Cuenta cuentaValida = cuentaService.validarLogin(cuentaFront);

        //Assert validar que el resultado es el esperado
        Assertions.assertFalse(cuentaValida.isError(), "El login no fue exitoso");
    }
}