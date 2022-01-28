package com.ndrewcoding.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    private final WebDriver driver;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to(URL_LOGIN);
    }

    public void fechar() {
        this.driver.quit();
    }

    public void preencherFormularioDeLogin(String username, String password) {
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    public void efetuarLogin() {
        WebElement formElement = driver.findElement(By.id("login-form"));
        formElement.submit();
    }

    public boolean estaNaPaginaDeLogin() {
        return driver.getCurrentUrl().equals(URL_LOGIN) || driver.getCurrentUrl().equals(URL_LOGIN.concat("?error"));
    }

    public String getNomeUsuarioLogado() {
        try {
            return driver.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public void naveParaPaginaDeLeiloes() {
        this.driver.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemText(String texto) {
        return driver.getPageSource().contains(texto);
    }

}
