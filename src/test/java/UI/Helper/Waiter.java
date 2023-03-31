package UI.Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static UI.Base.driver;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Waiter {
    private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    public static WebElement waitForElementToBeClickable(By by) {
        return wait.until(elementToBeClickable(by));
    }

    public static WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(visibilityOf(element));
    }


}
