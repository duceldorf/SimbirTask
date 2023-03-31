package UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Base {
    public static WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void start() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
    }

    @AfterTest
    public void stop() {
        driver.close();
    }
}
