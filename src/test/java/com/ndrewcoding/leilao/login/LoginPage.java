package com.ndrewcoding.leilao.login;

import com.ndrewcoding.leilao.PageObject;
import com.ndrewcoding.leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {

    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.webDriver.navigate().to(URL_LOGIN);
    }

    public void preencherFormularioDeLogin(String username, String password) {
        WebElement usernameInput = this.webDriver.findElement(By.id("username"));
        WebElement passwordInput = this.webDriver.findElement(By.id("password"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    public LeiloesPage efetuarLogin() {
        WebElement formElement = this.webDriver.findElement(By.id("login-form"));
        formElement.submit();
        return new LeiloesPage(this.webDriver);
    }

    public boolean estaNaPaginaDeLogin() {
        return this.webDriver.getCurrentUrl().equals(URL_LOGIN) || this.webDriver.getCurrentUrl().equals(URL_LOGIN.concat("?error"));
    }

    public String getNomeUsuarioLogado() {
        try {
            return this.webDriver.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public void naveParaPaginaDeLeiloes() {
        this.webDriver.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemText(String texto) {
        return this.webDriver.getPageSource().contains(texto);
    }

}
