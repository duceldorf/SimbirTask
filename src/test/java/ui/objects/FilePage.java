package ui.objects;

import org.openqa.selenium.By;

import java.util.ArrayList;

import static ui.Base.driver;
import static ui.helpers.Waiter.waitForElementToBeVisible;
import static org.testng.Assert.assertEquals;

public class FilePage {
    public final By textInFile = By.xpath("//div/p[@class='mg1']");

    public void checkTExtInFile(String fileText) {
        assertEquals(waitForElementToBeVisible(driver.findElement(textInFile)).getText(), fileText);
    }

    public void goToDiskPage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }
}
