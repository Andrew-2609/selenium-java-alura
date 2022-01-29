package com.ndrewcoding.leilao.leiloes;

import com.ndrewcoding.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage extends PageObject {

    public CadastroLeilaoPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void preencherFormularioDeCadastro(String nome, String dataAbertura, String valorInicial) {
        this.webDriver.findElement(By.id("nome")).sendKeys(nome);
        this.webDriver.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        this.webDriver.findElement(By.id("valorInicial")).sendKeys(valorInicial);
    }

    public LeiloesPage submeterFormularioDeCadastro() {
        this.webDriver.findElement(By.id("button-submit")).submit();
        return new LeiloesPage(this.webDriver);
    }

    public boolean estaExibindoMensagensDeValidacao() {
        String pageSource = this.webDriver.getPageSource();

        boolean minimoDeCaracteresAtingido = pageSource.contains("minimo");
        boolean campoEstaEmBranco = pageSource.contains("não deve estar em branco");
        boolean valorGrandeOSuficiente = pageSource.contains("deve ser um valor maior de");
        boolean dataEstaNoFormatoCorreto = pageSource.contains("deve ser uma data no formato");

        return minimoDeCaracteresAtingido && campoEstaEmBranco && valorGrandeOSuficiente && dataEstaNoFormatoCorreto;
    }

}
