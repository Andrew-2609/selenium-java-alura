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
    private CadastroLeilaoPage cadastroLeilaoPage;

    @BeforeEach
    void setUp() {
        LoginPage loginPage = new LoginPage();
        loginPage.preencherFormularioDeLogin("fulano", "pass");
        this.leiloesPage = loginPage.efetuarLogin();
        this.cadastroLeilaoPage = leiloesPage.carregarFormularioDeCadastroDeLeilao();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilão do dia ".concat(hoje);
        String valor = "500.00";

        this.cadastroLeilaoPage.preencherFormularioDeCadastro(nome, hoje, valor);

        this.leiloesPage = this.cadastroLeilaoPage.submeterFormularioDeCadastro();

        Assertions.assertTrue(this.leiloesPage.verificarSeLeilaoEstaCadastrado(nome, hoje, valor));
    }

    @Test
    public void deveriaValidarCadastroDeLeilao() {
        this.cadastroLeilaoPage.preencherFormularioDeCadastro("", "", "");

        this.cadastroLeilaoPage.submeterFormularioDeCadastro();

        Assertions.assertTrue(this.leiloesPage.estaNaPaginaAtual());
        Assertions.assertTrue(this.cadastroLeilaoPage.estaExibindoMensagensDeValidacao());
    }

    @AfterEach
    void teardown() {
        leiloesPage.fechar();
    }

}
