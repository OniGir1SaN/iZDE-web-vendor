package iZDEvendor.CreateObjectsTest;

import com.demoqa.entities.iZDEvendor.CreateObjectEntity.FacilityDescriptionEntity;
import com.demoqa.entities.iZDEvendor.LoginEntityV;
import com.demoqa.enums.iZDEvendor.EndpointsV;
import com.demoqa.utils.ConfigReader;
import iZDEvendor.BaseTestV;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddPictureTest extends BaseTestV {

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

    @BeforeMethod
    public void testRandomElementAmenitiesAndClick() {
        facilityDescriptionPage.randomElementTypeOfPlacementsAndClick();
        facilityDescriptionPage.randomElementLevelPlacementsAndClick();

        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        facilityDescriptionPage.fillUpFacilityDescription(entity);

        webElementActions.click(detailObjectPage.amenitiesBtn);

        detailObjectPage.selectRandomAmenitiesAndSave();

        webElementActions.click(detailObjectPage.closeListAmenitiesBtn);
        webElementActions.click(detailObjectPage.nextPageBtn);

    }

    @Test(groups = "functional")
    public void testUploadLessThanFivePictures() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomPicture(4);
        addPicturePage.fillUpUpload(entity);

        webElementActions.waitFor(2000);

        String errorMessage = detailObjectPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Выберите по меньшей мере 5 изображений"),
                "Ошибка не появилась или сообщение не соответствует ожидаемому: " + errorMessage);
    }

    @Test(groups = "functional")
    public void testUploadFivePictures() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomPicture(5);
        addPicturePage.fillUpUpload(entity);

        webElementActions.waitFor(200);

        boolean isHeaderVisible = webElementActions.isElementVisible(addPicturePage.getText());
        Assert.assertTrue(isHeaderVisible, "Заголовок 'Создайте правила для комфортного проживания' не найден!");
    }

    @Test(groups = "functional")
    public void testUploadTenPictures() {
        FacilityDescriptionEntity entity = randomUtilsV.generateRandomPicture(10);
        addPicturePage.fillUpUpload(entity);

        webElementActions.waitFor(204);

        boolean isHeaderVisible = webElementActions.isElementVisible(addPicturePage.getText());
        Assert.assertTrue(isHeaderVisible, "Заголовок 'Создайте правила для комфортного проживания' не найден!");
    }

    @AfterMethod
    public void refresh(){
        browserHelper.refreshPage();
        webElementActions.waitFor(2000);
    }
}