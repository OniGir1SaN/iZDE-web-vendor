package com.demoqa.pages.iZDEvendor;

import com.demoqa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainMenuPageV extends BasePage {

    @FindBy(className = "_logo_14h22_21")
    public WebElement logoBtn;

    @FindBy(className = "_btnUser_14h22_50")
    public WebElement profileBtn;

    @FindBy(className = "_burgerMenuIcon_14h22_45")
    public WebElement burgerMenuBtn;

    @FindBy(xpath = "(//button[@class='_btn_131i4_27'])[1]")
    public WebElement clientsBtn;

    @FindBy(xpath = "(//button[@class='_btn_131i4_27'])[2]")
    public WebElement myObjectsBtn;

    @FindBy(xpath = "(//button[@class='_btn_131i4_27'])[3]")
    public WebElement supportBtn;

    @FindBy(xpath = "//img[@alt='App Store']")
    public WebElement cellarAppStoreBtn;

    @FindBy(xpath = "//button[text()='Клиенты']")
    public WebElement cellarClientsBtn;

    @FindBy(xpath = "//button[text()='Мои Обьекты']")
    public WebElement cellarMyObjectsBtn;

    @FindBy(xpath = "//button[text()='Поддержка']")
    public WebElement cellarSupportBtn;

    public void clickLogoBtn(){
        webElementActions.click(logoBtn);
    }

    public void clickProfileBtn(){
        webElementActions.click(profileBtn);
    }

    public void clickBurgerMenuBtn(){
        webElementActions.click(burgerMenuBtn);
    }

    public void clickClientsBtn(){
        webElementActions.click(clientsBtn);
    }

    public void clickMyObjectsBtn(){
        webElementActions.click(myObjectsBtn);
    }

    public void clickSupportBtn(){
        webElementActions.click(supportBtn);
    }

    public void clickCellarAppStoreBtn(){
        webElementActions.click(cellarAppStoreBtn);
    }

    public void clickCellarClientsBtn(){
        webElementActions.click(cellarClientsBtn);
    }

    public void clickCellarMyObjectsBtn(){
        webElementActions.click(cellarMyObjectsBtn);
    }

    public void clickCellarSupportBtn(){
        webElementActions.click(cellarSupportBtn);
    }
}