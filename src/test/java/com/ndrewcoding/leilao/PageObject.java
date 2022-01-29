package com.ndrewcoding.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class PageObject {

    protected WebDriver webDriver;

    public PageObject(WebDriver webDriver) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");

        this.webDriver = webDriver == null ? new ChromeDriver() : webDriver;

        this.webDriver.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS)
        ;
    }

    public void fechar() {
        this.webDriver.quit();
    }

}
