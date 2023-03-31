package UI.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import static UI.Helper.Waiter.*;
import static org.testng.Assert.assertEquals;

public class FilePage {
    protected WebDriver driver;
    public FilePage(WebDriver driver) {
        this.driver = driver;
    }

    public final By textInFile = By.xpath("//div/p[@class='mg1']");

    public void checkTExtInFile(String fileText) {
        assertEquals(waitForElementToBeVisible(driver.findElement(textInFile)).getText(), fileText);
    }

    public DiskPage goToDiskPage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
        return new DiskPage(driver);
    }
}
