package UI.Tests;

import UI.Base;
import UI.Objects.DiskPage;
import UI.Objects.FilePage;
import UI.Objects.YandexLanding;
import org.testng.annotations.Test;

import static UI.Helper.Data.*;
import static org.testng.Assert.*;


public class Disk extends Base {
    DiskPage diskPage = new DiskPage(driver);
    FilePage filePage = new FilePage(driver);

    @Test(description = "Копирование файла в созданную папку")
    void copyFileInToNewPackage() {
        driver.get("http://ya.ru");
        YandexLanding yandexLanding = new YandexLanding(driver);
        yandexLanding
                .goToLoginPage()
                .loginWithCurrentUser();
        yandexLanding.goToDiskPage();
        diskPage.createFolder();
        diskPage.copyFileInToTheFolder();
        diskPage.openElement(folderName);
        assertEquals(
                diskPage.chooseElementInList(fileName).getText(),
                "Файл для\n" +
                "копир…я.docx");
        driver.findElement(diskPage.leftColumnSelectFile).click();
        diskPage.deleteFolder();
        diskPage.emptyTrash();
        diskPage.logout();
    }

    @Test(description = "Проверка текста в загруженном файле")
    void checkTextInLoadedFile() {
        driver.get("http://ya.ru");
        YandexLanding yandexLanding = new YandexLanding(driver);
        yandexLanding
                .goToLoginPage()
                .loginWithCurrentUser();
        yandexLanding.goToDiskPage();
        diskPage.createFolder();
        diskPage.openElement(folderName);
        diskPage.uploadFile();
        diskPage.openElement("somefile.txt");
        diskPage.goToFilePage()
                .checkTExtInFile("Text in the file");
        filePage.goToDiskPage();
        driver.findElement(diskPage.leftColumnSelectFile).click();
        diskPage.deleteFolder();
        diskPage.emptyTrash();
        diskPage.logout();
    }
}
