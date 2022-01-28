package com.ndrewcoding.leilao.leiloes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage {

    private final WebDriver driver;
    private static final String URL_LEILOES = "http://localhost:8080/login";

    public LeiloesPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to(URL_LEILOES);
    }

    public void fechar() {
        this.driver.quit();
    }

}
