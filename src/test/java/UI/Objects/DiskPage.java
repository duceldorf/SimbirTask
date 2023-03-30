package UI.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.ArrayList;

import static UI.Helper.Data.*;
import static UI.Helper.Waiter.*;

public class DiskPage {

    protected WebDriver driver;

    private final By profileButton = By.xpath("//a[@aria-label='Аккаунт']");
    private final By exitFromAccount = By.xpath("//ul[@class='menu__group']/li/a[@aria-label='Выйти из аккаунта']");
    private final By createButton = By.xpath("//span[contains(@class, 'create')]/button");
    private final By uploadFileButton = By.xpath("//input[@title='Загрузить файлы']");
    private final By progressBarHeader = By.xpath("//div[@class='uploader-progress__progress-info']/h3[contains(text(), 'Все файлы загружены')]");
    private final By folderCreate = By.xpath("//button[@aria-label='Папку']");
    private final By nameInput = By.xpath("//div[@class='dialog__body']//input");
    private final By confirmButton = By.xpath("//button[contains(@class, 'button_submit')]");

    public WebElement chooseElementInList(String elementName) {
        return driver.findElement(By.xpath(String.format("//div[@aria-label='%s']", elementName)));
    }

    /**
    Необходимый элемент из поп-апа выбирается по ключу параметра value:
     publish - поделиться
     view - просмотреть
     edit - редактировать
     download - скачать
     rename - переименовать
     move - переместить
     copy - копировать
     versions - история изменений
     delete - удалить
     */
    private WebElement chooseElementInPopup(String elementName) {
        return driver.findElement(By.xpath(String.format("//div[@value='%s']", elementName)));
    }

    public By leftColumnSelectFile = By.xpath("//nav[@class='LeftColumnNavigation']/span[@id='/disk']");

    private final By selectFolder = By.xpath(String.format("//div[@title='%s']", folderName));

    public WebElement notificationByText(String text) {
        return driver.findElement(By.xpath(String.format("//div[contains(text(), '%s')]", text)));
    }
    private final By cleanTrashButton = By.xpath("//button[contains(@class, 'clean')]");

    public DiskPage(WebDriver driver) {
        this.driver = driver;
    }
    public void openElement(String elementName) {
        Actions action = new Actions(driver);
        action.doubleClick(chooseElementInList(elementName)).perform();
    }

    public void createFolder() {
        driver.findElement(createButton).click();
        driver.findElement(folderCreate).click();
        driver.findElement(nameInput).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(nameInput).sendKeys(folderName);
        driver.findElement(confirmButton).click();
        waitForElementToBeVisible(notificationByText("Вы создали папку"));
    }

    public void copyFileInToTheFolder() {
        Actions action = new Actions(driver);
        action.contextClick(chooseElementInList(fileName)).perform();
        chooseElementInPopup("copy").click();
        driver.findElement(selectFolder).click();
        driver.findElement(confirmButton).click();
        waitForElementToBeVisible(notificationByText("Файл «"+fileName+"» скопирован в папку «"+folderName+"»"));
    }

    public void deleteFolder() {
        Actions action = new Actions(driver);
        action.contextClick(chooseElementInList(folderName)).perform();
        chooseElementInPopup("delete").click();
    }

    public void emptyTrash() {
        Actions action = new Actions(driver);
        action.doubleClick(chooseElementInList(trashBin)).perform();
        waitForElementToBeClickable(cleanTrashButton).click();
        driver.findElement(confirmButton).click();
    }

    public void uploadFile() {
        WebElement file = driver.findElement(uploadFileButton);
        file.sendKeys(System.getProperty("user.dir")+"\\src\\test\\resources\\somefile.txt");
        waitForElementToBeVisible(driver.findElement(progressBarHeader));
    }

    public LoginPage logout() {
        driver.findElement(profileButton).click();
        driver.findElement(exitFromAccount).click();
        return new LoginPage(driver);
    }

    public FilePage goToFilePage() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new FilePage(driver);
    }
}
