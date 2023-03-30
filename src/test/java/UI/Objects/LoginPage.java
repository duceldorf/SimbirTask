package UI.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    protected WebDriver driver;

    private final By loginWithMail = By.xpath("//button[@data-type='login']");
    private final By loginField = By.id("passp-field-login");
    private final By loginButton = By.id("passp:sign-in");
    private final By passwordField = By.id("passp-field-passwd");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Авторизация")) {
            throw new IllegalStateException("Wrong page open, current page is: " + driver.getCurrentUrl());
        }
    }

    public void loginWithCurrentUser() {
        driver.findElement(loginWithMail).click();
        driver.findElement(loginField).sendKeys("someloginfortesttask");
        driver.findElement(loginButton).click();
        driver.findElement(passwordField).sendKeys("GhblevfkGfhjkm");
        driver.findElement(loginButton).click();
    }


}
