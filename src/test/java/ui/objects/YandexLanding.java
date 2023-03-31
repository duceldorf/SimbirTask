package ui.objects;

import org.openqa.selenium.By;

import java.util.ArrayList;

import static ui.Base.driver;

public class YandexLanding {
    private final By loginButton = By.xpath("//div/a[@data-statlog='headline.enter']");
    private final By userMenu = By.xpath("//a[contains(@data-bem, 'usermenu')]");
    private final By diskButton = By.xpath("//a[contains(@class, 'disk')]");
    CaptchaPage captchaPage = new CaptchaPage();

    public YandexLanding() {
        if (driver.getTitle().equals("Ой!")) {
            driver.findElement(captchaPage.checkbox).click();
        }
    }

    public LoginPage goToLoginPage() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    public void goToDiskPage() {
        driver.findElement(userMenu).click();
        driver.findElement(diskButton).click();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(1));
    }

}
