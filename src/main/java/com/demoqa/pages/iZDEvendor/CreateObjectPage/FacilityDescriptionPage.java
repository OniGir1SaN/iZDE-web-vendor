package com.demoqa.pages.iZDEvendor.CreateObjectPage;

import com.demoqa.entities.iZDEvendor.CreateObjectEntity.FacilityDescriptionEntity;
import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FacilityDescriptionPage extends BasePage {

    @FindBy(xpath = "(//input[@type='text'])[1]")
    public WebElement objectNameInput;

    @FindBy(xpath = "(//div/textarea[@placeholder='Опишите объект'])[2]")
    public WebElement objectDescriptionInput;

    @FindBy(xpath = "//button[text()='Выберите вид размещения']")
    public WebElement typesOfPlacementsBtn;

    @FindBy(xpath = "//button[text()='Гостевой дом (guest home)']")
    public WebElement bungalowBtn;

    @FindBy(id = "btn_select_option")
    public List<WebElement> listTypesOfPlacements;

    @FindBy(id = "btn_select_option")
    public List<WebElement> listLevelPlacements;

    @FindBy(xpath = "//button[text()='Выберите тип размещения']")
    public WebElement levelPlacementsBtn;

    @FindBy(xpath = "//input[@placeholder='Введите название объекта']")
    public WebElement objectNamePlaceholder;

    @FindBy(xpath = "(//div/textarea[@placeholder='Опишите объект'])[2]")
    public WebElement objectDescriptionPlaceholder;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div[2]/div[2]/div[3]/div[1]/button/text()")
    public WebElement TypesOfPlacementsPlaceholder;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div/div[2]/div[2]/div[3]/div[2]/button/text()")
    public WebElement levelPlacementsPlaceholder;

    @FindBy(className = "_message_94tdj_6")
    public WebElement errorMessage;

    @FindBy(className = "_button_4xwx9_17")
    public WebElement nextPageBtn;

    public String getErrorMessage() {
        if (errorMessage.isDisplayed()) {
            return errorMessage.getText();
        }
        return "";
    }

    public void clickBungalow(){
        webElementActions.click(typesOfPlacementsBtn)
                .click(bungalowBtn);
    }

    public void randomElementTypeOfPlacementsAndClick() {
        webElementActions.click(typesOfPlacementsBtn);
        WebElement randomElement = webElementActions.randomElementSelection(listTypesOfPlacements);
        webElementActions.click(randomElement);
    }

    public void randomElementLevelPlacementsAndClick() {
        webElementActions.click(levelPlacementsBtn);
        WebElement randomElement = webElementActions.randomElementSelection(listLevelPlacements);
        webElementActions.click(randomElement);
    }

    public FacilityDescriptionPage fillUpFacilityDescription(FacilityDescriptionEntity facilityDescriptionEntity) {
        webElementActions.sendKeys(objectNameInput, facilityDescriptionEntity.getObjectNameInput())
                .sendKeys(objectDescriptionInput, facilityDescriptionEntity.getObjectDescriptionInput())
                .click(nextPageBtn);
        return this;
    }

    public void verifyFacilityDescriptionPlaceholders() {
        WebElement[] elements = {objectNamePlaceholder, objectDescriptionPlaceholder};
        String[] expectedPlaceholders = {"Введите название объекта", "Опишите объект"};
        String[] fieldNames = {"Введите название объекта", "Опишите объект"};

        webElementActions.verifyPlaceholders(elements, expectedPlaceholders, fieldNames);
    }
}