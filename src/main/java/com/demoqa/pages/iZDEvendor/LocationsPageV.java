package com.demoqa.pages.iZDEvendor;

import com.demoqa.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class LocationsPageV extends BasePage {

    @FindBy(xpath = "//p[text()='Создать объект']")
    public WebElement createObjectBtn;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement searchInput;

    @Getter
    @FindBy(xpath = "//p[@class='_header_sdb7x_18']")
    public List<WebElement> locationNames;

    @FindBy(className = "_notActivePaginationForQA_8rpsj_35")
    public List<WebElement> paginationBtn;

    @FindBy(xpath = "//button[@class='_activePage_lh27r_29']")
    public WebElement activePagination;

    public void clickCreateObject() {
        webElementActions.click(createObjectBtn);
    }

    public void randomElementSelectionAndClick() {
        WebElement randomElement = webElementActions.randomElementSelection(locationNames);
        webElementActions.click(randomElement);
    }

    public void randomPaginationAndClick() {
        WebElement randomPaginationButton = webElementActions.randomElementSelection(paginationBtn);

        webElementActions.click(randomPaginationButton);

        String paginationButtonText = randomPaginationButton.getText();
        WebElement activePaginationButton = activePagination;
        Assert.assertTrue(activePaginationButton.getText().equals(paginationButtonText),
                "Активная пагинация не соответствует выбранной: " + paginationButtonText);
    }

    public String randomElementSelectionAndGetName() {
        WebElement randomElement = webElementActions.randomElementSelection(locationNames);
        String locationName = randomElement.getText();
        return locationName;
    }

    public void searchLocation(String locationName) {
        searchInput.clear();
        searchInput.sendKeys(locationName);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}