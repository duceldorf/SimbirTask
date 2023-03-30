package UI.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import static UI.Helper.Waiter.*;

public class FilePage {
    protected WebDriver driver;
    public FilePage(WebDriver driver) {
        this.driver = driver;
    }

    public final By textInFile = By.xpath("//div/p[@class='mg1']");
    private final By profileButton = By.xpath("//div[contains(@class, 'user2')]/a[contains(@class, 'user-account')]");
    private final By exitButton = By.xpath("//ul[@class='menu__group']/li/a[contains(@class, 'exit')]");

    public void checkTExtInFile(String fileText) {
        Assert.assertEquals(waitForElementToBeVisible(driver.findElement(textInFile)).getText(), fileText);
    }

    public DiskPage goToDiskPage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
        return new DiskPage(driver);
    }
}
