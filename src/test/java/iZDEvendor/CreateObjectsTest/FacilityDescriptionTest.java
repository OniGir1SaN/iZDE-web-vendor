package iZDEvendor.CreateObjectsTest;

import com.demoqa.entities.iZDEvendor.CreateObjectEntity.FacilityDescriptionEntity;
import com.demoqa.entities.iZDEvendor.LoginEntityV;
import com.demoqa.enums.iZDEvendor.EndpointsV;
import com.demoqa.utils.ConfigReader;
import iZDEvendor.BaseTestV;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FacilityDescriptionTest extends BaseTestV {

    @BeforeClass
    public void loginOnce() {
        browserHelper.open(ConfigReader.getValue("baseURLV") + EndpointsV.SIGNIN.getEndpoint());

        LoginEntityV loginEntityV = randomUtilsV.validLoginEntity();
        loginPageV.fillUpLoginForm(loginEntityV);

        mainMenuPageV.clickCellarMyObjectsBtn();
        locationsPage.clickCreateObject();

        webElementActions.waitFor(2000);
        locationsPage.randomElementSelectionAndClick();

        String currentUrl = browserHelper.getCurrentUrl();
        String expectedUrlPattern = "https://test-vendor\\.izde\\.online/locations/[a-f0-9\\-]+/create-object";

        Assert.assertTrue(currentUrl.matches(expectedUrlPattern),
                "URL не соответствует ожидаемому шаблону: " + currentUrl);

    }

    private void submitFormAndVerifyError(FacilityDescriptionEntity entity, String expectedErrorMessage) {
        facilityDescriptionPage.fillUpFacilityDescription(entity);

        String errorMessage = facilityDescriptionPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
                "Ожидаемая ошибка: " + expectedErrorMessage + ", получено: " + errorMessage);
        Allure.step("Проверка ошибки: " + expectedErrorMessage);
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionPlaceholders() {
        facilityDescriptionPage.verifyFacilityDescriptionPlaceholders();
        Allure.step("Проверка плейсхолдеров полей описания объекта");
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithEmptyFields() {
        FacilityDescriptionEntity entity = new FacilityDescriptionEntity();
        entity.setObjectNameInput("");
        entity.setObjectDescriptionInput("");

        submitFormAndVerifyError(entity, "Введите название объекта");
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithEmptyFieldsP() {
        FacilityDescriptionEntity entity = new FacilityDescriptionEntity();
        entity.setObjectNameInput("          ");
        entity.setObjectDescriptionInput("          ");

        submitFormAndVerifyError(entity, "Введите название объекта");
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithEmptyName() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        entity.setObjectNameInput("");

        submitFormAndVerifyError(entity, "Введите название объекта");
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithEmptyType() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        facilityDescriptionPage.randomElementTypeOfPlacementsAndClick();

        submitFormAndVerifyError(entity, "Выберите тип объекта");

        browserHelper.refreshPage();
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithEmptyLevel() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        facilityDescriptionPage.randomElementLevelPlacementsAndClick();

        submitFormAndVerifyError(entity, "Выберите вид объекта");
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithNameAndDescription() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        entity.setObjectNameInput("");
        entity.setObjectDescriptionInput("");

        facilityDescriptionPage.randomElementTypeOfPlacementsAndClick();
        facilityDescriptionPage.randomElementLevelPlacementsAndClick();

        submitFormAndVerifyError(entity, "Введите название объекта");
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithNameAndType() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        entity.setObjectNameInput("");

        facilityDescriptionPage.randomElementLevelPlacementsAndClick();

        submitFormAndVerifyError(entity, "Введите название объекта");
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithNameAndLevel() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        entity.setObjectNameInput("");

        facilityDescriptionPage.randomElementTypeOfPlacementsAndClick();

        submitFormAndVerifyError(entity, "Введите название объекта");
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithDescriptionAndLevel() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        entity.setObjectDescriptionInput("");

        facilityDescriptionPage.randomElementTypeOfPlacementsAndClick();

        submitFormAndVerifyError(entity, "Введите описание объекта");
    }

    @Test(groups = "validation")
    public void testFacilityDescriptionWithTypeAndLevel() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        submitFormAndVerifyError(entity, "Выберите вид объекта");
    }

    @AfterMethod
    public void refreshPage() {
        browserHelper.refreshPage();
        webElementActions.waitFor(2000);
    }

}