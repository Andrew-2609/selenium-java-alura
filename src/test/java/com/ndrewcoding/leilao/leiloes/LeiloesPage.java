package com.ndrewcoding.leilao.leiloes;

import com.ndrewcoding.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage extends PageObject {

    private static final String URL_LEILOES = "http://localhost:8080/leiloes";

    public LeiloesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CadastroLeilaoPage carregarFormularioDeCadastroDeLeilao() {
        this.webDriver.navigate().to(URL_LEILOES.concat("/new"));
        return new CadastroLeilaoPage(webDriver);
    }

    public boolean verificarSeLeilaoEstaCadastrado(String nome, String dataAbertura, String valorInicial) {
        WebElement ultimaLinhaDaTabelaDeLeiloes = this.webDriver.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = ultimaLinhaDaTabelaDeLeiloes.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = ultimaLinhaDaTabelaDeLeiloes.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = ultimaLinhaDaTabelaDeLeiloes.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome) && colunaDataAbertura.getText().equals(dataAbertura) && colunaValorInicial.getText().equals(valorInicial);
    }

    public boolean estaNaPaginaAtual() {
        return this.webDriver.getCurrentUrl().equals(URL_LEILOES);
    }

}
