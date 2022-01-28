package com.ndrewcoding.leilao.leiloes;

import org.openqa.selenium.WebDriver;

public class LeiloesPage {

    private final WebDriver driver;

    public LeiloesPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void fechar() {
        this.driver.quit();
    }

}
