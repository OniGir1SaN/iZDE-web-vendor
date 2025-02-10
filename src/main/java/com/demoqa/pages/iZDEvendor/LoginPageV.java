package com.demoqa.pages.iZDEvendor;

import com.demoqa.entities.iZDEvendor.LoginEntityV;
import com.demoqa.pages.BasePage;
import com.demoqa.pages.iZDE.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageV extends BasePage {

    @FindBy(id = "emailOrPhone")
    public WebElement emailOrPhoneInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(xpath = "//input[@placeholder='+996']")
    public WebElement emailOrPhonePlaceholder;

    @FindBy(xpath = "//input[@placeholder='Введите пароль']")
    public WebElement passwordPlaceholder;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitBtn;

    @FindBy(xpath = "//p[@class='_authInputErrorText_9mx2m_50']")
    public WebElement errorMessageElement;

    @FindBy(xpath = "//p[@class='GlobalErrorInputMessage']")
    public WebElement errorMessageElement2;

    public String getErrorMessage() {
        if (errorMessageElement.isDisplayed()) {
            return errorMessageElement.getText();
        }
        return "";
    }

    public String getErrorMessage2() {
        if (errorMessageElement2.isDisplayed()) {
            return errorMessageElement2.getText();
        }
        return "";
    }

    public LoginPage fillUpLoginForm(LoginEntityV loginEntityV) {
        webElementActions.sendKeys(emailOrPhoneInput, loginEntityV.getEmailOrPhone())
                .sendKeysWithEnter(passwordInput, loginEntityV.getPassword());
        return new LoginPage();
    }

    public void verifyEmailAndPasswordPlaceholders() {
        WebElement[] elements = {emailOrPhonePlaceholder, passwordPlaceholder};
        String[] expectedPlaceholders = {"+996", "Введите пароль"};
        String[] fieldNames = {"+996", "Введите пароль"};

        webElementActions.verifyPlaceholders(elements, expectedPlaceholders, fieldNames);
    }
}
