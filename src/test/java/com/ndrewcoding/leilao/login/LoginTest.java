package com.ndrewcoding.leilao.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.navigate().to("http://localhost:8080/login");

        driver.findElement(By.id("username")).sendKeys("fulano");

        driver.findElement(By.id("password")).sendKeys("pass");

        driver.findElement(By.id("login-form")).submit();

        Assertions.assertNotEquals("http://localhost:8080/login", driver.getCurrentUrl());
        Assertions.assertEquals("fulano", driver.findElement(By.id("usuario-logado")).getText());

        driver.quit();
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.navigate().to("http://localhost:8080/login");

        driver.findElement(By.id("username")).sendKeys("invalido");

        driver.findElement(By.id("password")).sendKeys("123");

        driver.findElement(By.id("login-form")).submit();

        Assertions.assertEquals("http://localhost:8080/login?error", driver.getCurrentUrl());
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.findElement(By.id("usuario-logado")));
        Assertions.assertTrue(driver.getPageSource().contains("Usuário e senha inválidos."));

        driver.quit();
    }

}
