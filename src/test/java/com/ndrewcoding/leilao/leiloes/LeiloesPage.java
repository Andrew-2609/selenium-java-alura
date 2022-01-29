package com.ndrewcoding.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public boolean verificarSeLeilaoEstaCadastrado(String nome, String dataAbertura, String valorInicial) {
        WebElement ultimaLinhaDaTabelaDeLeiloes = this.driver.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = ultimaLinhaDaTabelaDeLeiloes.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = ultimaLinhaDaTabelaDeLeiloes.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = ultimaLinhaDaTabelaDeLeiloes.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome) && colunaDataAbertura.getText().equals(dataAbertura) && colunaValorInicial.getText().equals(valorInicial);
    }

}
