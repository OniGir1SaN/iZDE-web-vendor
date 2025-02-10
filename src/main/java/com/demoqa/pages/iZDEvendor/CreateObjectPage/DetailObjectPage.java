package com.demoqa.pages.iZDEvendor.CreateObjectPage;

import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class DetailObjectPage extends BasePage {

    @FindBy(xpath = "(//button/img[@alt='Minus'])[1]")
    public WebElement numberOfRoomsMinus;

    @FindBy(xpath = "(//button/img[@alt='Plus'])[1]")
    public WebElement numberOfRoomsPlus;

    @FindBy(xpath = "(//div[@class='_wrapper_9qltg_12'])[1]")
    public WebElement numberOfRooms;

    @FindBy(xpath = "(//button/img[@alt='Minus'])[2]")
    public WebElement capacityMinus;

    @FindBy(xpath = "(//button/img[@alt='Plus'])[2]")
    public WebElement capacityPlus;

    @FindBy(xpath = "(//div[@class='_wrapper_9qltg_12'])[2]")
    public WebElement capacity;

    @FindBy(className = "_selectButton_bgf7e_12")
    public WebElement amenitiesBtn;

    @FindBy(className = "_item_bgf7e_89")
    public List<WebElement> listAmenitiesBtn;

    @FindBy(xpath = "//img[@alt='Close']")
    public WebElement closeListAmenitiesBtn;

    @FindBy(className = "_button_4xwx9_17")
    public WebElement nextPageBtn;

    @FindBy(className = "_message_94tdj_6")
    public WebElement errorMessage;

    public String getErrorMessage() {
        return errorMessage.isDisplayed() ? errorMessage.getText() : "";
    }

    public void clickNextPageBtn() {
        webElementActions.click(nextPageBtn);
    }

    public List<String> selectRandomAmenitiesAndSave() {
        List<WebElement> randomElements = webElementActions.getRandomElements(listAmenitiesBtn);

        for (WebElement element : randomElements) {
            webElementActions.click(element);
        }

        return randomElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getSelectedAmenitiesText() {
        return List.of(amenitiesBtn.getText().split(", "));
    }
}
