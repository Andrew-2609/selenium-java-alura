package com.ndrewcoding.leilao.leiloes;

import com.ndrewcoding.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeiloesTest {

    private LeiloesPage leiloesPage;

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        loginPage.preencherFormularioDeLogin("fulano", "pass");
        leiloesPage = loginPage.efetuarLogin();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        CadastroLeilaoPage cadastroLeilaoPage = leiloesPage.carregarFormularioDeCadastroDeLeilao();
    }

    @AfterEach
    void teardown() {
        leiloesPage.fechar();
    }

}
