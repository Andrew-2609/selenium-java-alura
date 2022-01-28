package com.ndrewcoding.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSeleniumTest {

    @Test
    public void helloWorld() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        webDriver.navigate().to("http://localhost:8080/leilao");
        webDriver.quit();
    }

}
