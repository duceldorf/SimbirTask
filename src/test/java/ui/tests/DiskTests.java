package ui.tests;

import ui.Base;
import ui.objects.DiskPage;
import ui.objects.FilePage;
import ui.objects.YandexLanding;
import org.testng.annotations.Test;

import static ui.helpers.Data.fileName;
import static ui.helpers.Data.folderName;
import static org.testng.Assert.assertEquals;


public class DiskTests extends Base {
    DiskPage diskPage = new DiskPage();
    FilePage filePage = new FilePage();

    @Test(description = "Копирование файла в созданную папку")
    void copyFileInToNewPackage() {
        driver.get("http://ya.ru");
        YandexLanding yandexLanding = new YandexLanding();
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
        YandexLanding yandexLanding = new YandexLanding();
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
