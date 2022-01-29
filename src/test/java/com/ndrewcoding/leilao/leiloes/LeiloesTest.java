package com.ndrewcoding.leilao.leiloes;

import com.ndrewcoding.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia ".concat(hoje);
        String valor = "500.00";

        cadastroLeilaoPage.preencherFormularioDeCadastro(nome, hoje, valor);

        this.leiloesPage = cadastroLeilaoPage.submeterFormularioDeCadastro();

        Assertions.assertTrue(this.leiloesPage.verificarSeLeilaoEstaCadastrado(nome, hoje, valor));
    }

    @AfterEach
    void teardown() {
        leiloesPage.fechar();
    }

}
