package com.ndrewcoding.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage {

    private final WebDriver driver;

    public CadastroLeilaoPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void preencherFormularioDeCadastro(String nome, String dataAbertura, String valorInicial) {
        this.driver.findElement(By.id("nome")).sendKeys(nome);
        this.driver.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        this.driver.findElement(By.id("valorInicial")).sendKeys(valorInicial);
    }

    public LeiloesPage submeterFormularioDeCadastro() {
        this.driver.findElement(By.id("button-submit")).submit();
        return new LeiloesPage(this.driver);
    }

}
