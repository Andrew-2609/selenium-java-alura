package com.ndrewcoding.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {

    protected WebDriver webDriver;

    public PageObject(WebDriver webDriver) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");

        this.webDriver = webDriver == null ? new ChromeDriver() : webDriver;
    }

    public void fechar() {
        this.webDriver.quit();
    }

}
