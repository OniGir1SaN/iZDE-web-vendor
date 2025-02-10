package iZDEvendor;

import com.demoqa.entities.iZDEvendor.LoginEntityV;
import com.demoqa.enums.iZDEvendor.EndpointsV;
import com.demoqa.utils.ConfigReader;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestV extends BaseTestV {

    @BeforeMethod
    public void openLoginPage() {
        browserHelper.open(ConfigReader.getValue("baseURLV") + EndpointsV.SIGNIN.getEndpoint());
        Allure.step("Открыта страница авторизации");
    }

    private void submitFormAndVerifyError(LoginEntityV entity, String expectedErrorMessage, boolean isPasswordError) {
        loginPageV.fillUpLoginForm(entity);

        String errorMessage;
        if (isPasswordError) {
            errorMessage = loginPageV.getErrorMessage2();
        } else {
            errorMessage = loginPageV.getErrorMessage();
        }

        Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
                "Ожидаемая ошибка: " + expectedErrorMessage + ", получено: " + errorMessage);
        Allure.step("Проверка ошибки: " + expectedErrorMessage);
    }

    @Test(groups = "validation")
    public void testLoginPlaceholders() {
        loginPageV.verifyEmailAndPasswordPlaceholders();
        Allure.step("Проверка плейсхолдеров полей email и пароля");
    }

    @Test(groups = "validation")
    public void testLoginWithEmptyEmail() {
        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setEmailOrPhone("");
        submitFormAndVerifyError(entity, "Обязательно к заполнению", false);
    }

    @Test(groups = "validation")
    public void testLoginWithInvalidEmailFormat() {
        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setEmailOrPhone("invalidemail"); // Неверный формат email
        submitFormAndVerifyError(entity, "Введите верный Email или номер телефона", false);
    }

    @Test(groups = "validation")
    public void testLoginWithEmptyPassword() {
        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setPassword("");
        submitFormAndVerifyError(entity, "Обязательно к заполнению", false);
    }

    @Test(groups = "validation")
    public void testLoginWithShortPassword() {
        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setPassword("short");
        submitFormAndVerifyError(entity,"неправильный логин или пароль", true);
    }

    @Test(groups = "validation")
    public void testLoginWithInvalidCredentials() {
        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setEmailOrPhone("invalidemail@example.com");
        entity.setPassword("InvalidPassword123!");
        submitFormAndVerifyError(entity, "неправильный логин или пароль", true);
    }

    @Test(groups = "validation")
    public void testLoginWithEmptyFields() {
        LoginEntityV entity = new LoginEntityV();
        entity.setEmailOrPhone("");
        entity.setPassword("");
        submitFormAndVerifyError(entity, "Обязательно к заполнению", false);
    }

    @Test(groups = "validation")
    public void testLoginWithLongEmail() {
        String longEmail = "a".repeat(128) + "@example.com";
        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setEmailOrPhone(longEmail);
        submitFormAndVerifyError(entity, "неправильный логин или пароль", true);
    }

    @Test(groups = "validation")
    public void testLoginWithLongPassword() {
        String longPassword = "a".repeat(128);
        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setPassword(longPassword);
        submitFormAndVerifyError(entity, "неправильный логин или пароль", true);
    }

    @Test(groups = "validation")
    public void testLoginWithLongEmailAndPassword() {
        String longEmail = "a".repeat(128) + "@example.com";
        String longPassword = "a".repeat(128);

        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setEmailOrPhone(longEmail);
        entity.setPassword(longPassword);

        submitFormAndVerifyError(entity, "неправильный логин или пароль", true);
    }

    @Test(groups = "validation")
    public void testLoginWithVeryLongEmail() {
        String veryLongEmail = "a".repeat(255) + "@example.com";
        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setEmailOrPhone(veryLongEmail);
        submitFormAndVerifyError(entity, "неправильный логин или пароль", true);
    }

    @Test(groups = "validation")
    public void testLoginWithVeryLongPassword() {
        String veryLongPassword = "a".repeat(255);
        LoginEntityV entity = randomUtilsV.generateRandomLoginEntityV();
        entity.setPassword(veryLongPassword);
        submitFormAndVerifyError(entity, "неправильный логин или пароль", true);
    }
}