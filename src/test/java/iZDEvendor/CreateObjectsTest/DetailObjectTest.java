package iZDEvendor.CreateObjectsTest;

import com.demoqa.entities.iZDEvendor.CreateObjectEntity.FacilityDescriptionEntity;
import com.demoqa.entities.iZDEvendor.LoginEntityV;
import com.demoqa.enums.iZDEvendor.EndpointsV;
import com.demoqa.utils.ConfigReader;
import iZDEvendor.BaseTestV;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DetailObjectTest extends BaseTestV {

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


        facilityDescriptionPage.clickBungalow();
        facilityDescriptionPage.randomElementLevelPlacementsAndClick();

        FacilityDescriptionEntity entity = randomUtilsV.generateRandomFacilityDescriptionEntity();
        facilityDescriptionPage.fillUpFacilityDescription(entity);
    }

    @Test(groups = "functional")
    public void testSelectedAmenitiesAreDisplayed() {
        webElementActions.click(detailObjectPage.amenitiesBtn);

        List<String> selectedTexts = detailObjectPage.selectRandomAmenitiesAndSave();

        webElementActions.click(detailObjectPage.closeListAmenitiesBtn);

        List<String> displayedTexts = detailObjectPage.getSelectedAmenitiesText();

        webElementActions.waitFor(2000);
        webElementActions.pause(200000);

        Assert.assertEquals(displayedTexts, selectedTexts, "Выбранные удобства не совпадают с отображаемыми!");
    }


    @Test(groups = "functional")
    public void testNextPageWithoutSelectingAmenities() {
        detailObjectPage.clickNextPageBtn();

        String errorMessage = detailObjectPage.getErrorMessage();

        Assert.assertEquals(errorMessage, "Выберите по меньшей мере одно удобство",
                "Сообщение об ошибке не соответствует ожидаемому!");

        webElementActions.waitFor(2000);
    }

    @Test(groups = "functional")
    public void testNumberOfRoomsIncreaseAndDecrease() {
        webElementActions.increaseAndDecreaseValue(detailObjectPage.numberOfRoomsPlus, detailObjectPage.numberOfRoomsMinus, detailObjectPage.numberOfRooms);
    }

    @Test(groups = "functional")
    public void testCapacityIncreaseAndDecrease() {
        webElementActions.increaseAndDecreaseValue(detailObjectPage.capacityPlus, detailObjectPage.capacityMinus, detailObjectPage.capacity);
    }


}