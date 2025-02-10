package com.demoqa.pages.iZDEvendor.CreateObjectPage;

import com.demoqa.entities.iZDEvendor.CreateObjectEntity.RegulationsEntity;
import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegulationsPage extends BasePage {

    @FindBy(xpath = "(//input[@type='text'])[1]")
    public WebElement regulationsInput;

    @FindBy(xpath = "(//input[@type='text'])[2]")
    public WebElement cancellationInput;

    @FindBy(xpath = "//input[@placeholder='Введите правила объекта']")
    public WebElement regulationsPlaceholder;

    @FindBy(xpath = "//input[@placeholder='Введите правила отмены бронирования']")
    public WebElement cancellationPlaceholder;

    @FindBy(className = "_button_4xwx9_17")
    public WebElement nextPageBtn;

    @FindBy(className = "_message_94tdj_6")
    public WebElement errorMessage;

    public String getErrorMessage() {
        if (errorMessage.isDisplayed()) {
            return errorMessage.getText();
        }
        return "";
    }

   public RegulationsPage fillUpRegulationsForm (RegulationsEntity regulationsEntity){
        webElementActions.sendKeys(regulationsInput, regulationsEntity.getRegulationsInput())
                .sendKeys(cancellationInput, regulationsEntity.getCancellationInput())
                .click(nextPageBtn);
        return this;
   }

    public void clickNextPage() {
        nextPageBtn.click();
    }
}
