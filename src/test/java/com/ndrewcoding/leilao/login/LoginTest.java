package com.ndrewcoding.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        this.loginPage = new LoginPage();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        loginPage.preencherFormularioDeLogin("fulano", "pass");

        loginPage.efetuarLogin();

        Assertions.assertFalse(loginPage.estaNaPaginaDeLogin());
        Assertions.assertEquals("fulano", loginPage.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        loginPage.preencherFormularioDeLogin("invalido", "123");

        loginPage.efetuarLogin();

        Assertions.assertTrue(loginPage.estaNaPaginaDeLogin());
        Assertions.assertTrue(loginPage.contemText("Usuário e senha inválidos."));
        Assertions.assertNull(loginPage.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        loginPage.naveParaPaginaDeLeiloes();

        Assertions.assertFalse(loginPage.contemText("Dados do Leilão"));
        Assertions.assertTrue(loginPage.estaNaPaginaDeLogin());
    }

    @AfterEach
    void teardown() {
        loginPage.fechar();
    }

}
