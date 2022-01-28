package com.ndrewcoding.leilao.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private WebDriver driver;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
    }

    @BeforeEach
    void setUp() {
        this.driver = new ChromeDriver();
        this.driver.navigate().to(URL_LOGIN);
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement formElement = driver.findElement(By.id("login-form"));

        String username = "fulano";
        String password = "pass";

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        formElement.submit();

        Assertions.assertNotEquals(URL_LOGIN, driver.getCurrentUrl());
        Assertions.assertEquals(username, driver.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos() {
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement formElement = driver.findElement(By.id("login-form"));

        usernameInput.sendKeys("invalido");
        passwordInput.sendKeys("123");
        formElement.submit();

        Assertions.assertEquals(URL_LOGIN.concat("?error"), driver.getCurrentUrl());
        Assertions.assertThrows(NoSuchElementException.class, () -> driver.findElement(By.id("usuario-logado")));
        Assertions.assertTrue(driver.getPageSource().contains("Usuário e senha inválidos."));
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
