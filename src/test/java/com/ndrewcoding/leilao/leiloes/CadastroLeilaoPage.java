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

    public boolean estaExibindoMensagensDeValidacao() {
        String pageSource = this.driver.getPageSource();

        boolean minimoDeCaracteresAtingido = pageSource.contains("minimo");
        boolean campoEstaEmBranco = pageSource.contains("não deve estar em branco");
        boolean valorGrandeOSuficiente = pageSource.contains("deve ser um valor maior de");
        boolean dataEstaNoFormatoCorreto = pageSource.contains("deve ser uma data no formato");

        return minimoDeCaracteresAtingido && campoEstaEmBranco && valorGrandeOSuficiente && dataEstaNoFormatoCorreto;
    }

}
