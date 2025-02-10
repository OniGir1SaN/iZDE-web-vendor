package com.demoqa.pages.iZDEvendor.CreateObjectPage;

import com.demoqa.entities.iZDEvendor.CreateObjectEntity.FacilityDescriptionEntity;
import com.demoqa.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddPicturePage extends BasePage {

    @FindBy(className = "_button_4xwx9_17")
    public WebElement nextPageBtn;

    @FindBy(className = "_btnAvatar_1gj8u_36")
    public WebElement uploadPictureBtn;

    @FindBy(className = "_image_ti8i9_12")
    public List<WebElement> listPicture;

    @FindBy(className = "_message_94tdj_6")
    public WebElement errorMessage;

    @FindBy(xpath = "//h3[text()='Создайте правила для комфортного проживания']")
    public WebElement text;

    public WebElement getText() {
        return text;
    }

    public String getErrorMessage() {
        if (errorMessage.isDisplayed()) {
            return errorMessage.getText();
        }
        return "";
    }

    public void clickNextPageBtn() {
        webElementActions.click(nextPageBtn);
    }

    public boolean verifyUploadedPictures(int expectedCount) {
        List<WebElement> pictures = listPicture;

        if (pictures.size() == expectedCount) {
            System.out.println("Фотографий загружено корректное количество: " + expectedCount);
            return true;
        } else {
            System.out.println("Ошибка! Ожидалось " + expectedCount + " фотографий, но найдено " + pictures.size());
            return false;
        }
    }


    public AddPicturePage fillUpUpload(FacilityDescriptionEntity detailObjectPage) {
        String[] filePaths = detailObjectPage.getDownloadPictures();

        if (filePaths != null && filePaths.length > 0) {
            for (String filePath : filePaths) {
                if (filePath != null && !filePath.isEmpty()) {
                    try {
                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("upload-button")));

                        if (!fileInput.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", fileInput);
                        }

                        fileInput.sendKeys(filePath);
                        System.out.println("Файл успешно загружен: " + filePath);

                        webElementActions.click(uploadPictureBtn);
                        webElementActions.waitFor(1000);

                    } catch (Exception e) {
                        System.err.println("Ошибка загрузки файла: " + e.getMessage());
                    }
                }
            }

            webElementActions.click(nextPageBtn);

        } else {
            System.out.println("Файлы для загрузки не найдены.");
        }

        return this;
    }
}
