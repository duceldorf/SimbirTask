package ui.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ui.Base.driver;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Waiter {

    public static WebElement waitForElementToBeClickable(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(elementToBeClickable(by));
    }

    public static WebElement waitForElementToBeVisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(visibilityOf(element));
    }


}
