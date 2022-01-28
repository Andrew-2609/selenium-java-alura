package com.ndrewcoding.leilao.leiloes;

import org.openqa.selenium.WebDriver;

public class LeiloesPage {

    private final WebDriver driver;
    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

    public LeiloesPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void fechar() {
        this.driver.quit();
    }

    public CadastroLeilaoPage carregarFormularioDeCadastroDeLeilao() {
        this.driver.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(driver);
    }

}
