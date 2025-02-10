package iZDEvendor;

import com.demoqa.entities.iZDEvendor.LoginEntityV;
import com.demoqa.enums.iZDEvendor.EndpointsV;
import com.demoqa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LocationsTest extends BaseTestV {

    private String savedLocationName;

    @BeforeMethod
    public void loginOnce() {
        browserHelper.open(ConfigReader.getValue("baseURLV") + EndpointsV.SIGNIN.getEndpoint());

        LoginEntityV loginEntityV = randomUtilsV.validLoginEntity();
        loginPageV.fillUpLoginForm(loginEntityV);

        mainMenuPageV.clickCellarMyObjectsBtn();
        locationsPage.clickCreateObject();
    }

    @Test
    public void openCreateObjectsPage() {
        locationsPage.randomElementSelectionAndClick();

        String currentUrl = browserHelper.getCurrentUrl();

        String expectedUrlPattern = "https://test-vendor\\.izde\\.online/locations/[a-f0-9\\-]+/create-object";

        Assert.assertTrue(currentUrl.matches(expectedUrlPattern),
                "URL не соответствует ожидаемому шаблону: " + currentUrl);
    }

    @Test
    public void testSearchLocation() {
        savedLocationName = locationsPage.randomElementSelectionAndGetName();
        locationsPage.searchLocation(savedLocationName);

        List<WebElement> searchResults = locationsPage.getLocationNames();
        Assert.assertEquals(searchResults.size(), 1, "Должна быть найдена только одна локация с именем: " + savedLocationName);
    }

    @Test
    public void testSearchLocationWithInvalidName() {
        String invalidLocationName = "Неизвестная Локация";
        locationsPage.searchLocation(invalidLocationName);

        List<WebElement> searchResults = locationsPage.getLocationNames();

        Assert.assertTrue(searchResults.isEmpty(), "По запросу '" + invalidLocationName + "' не должно быть найдено локаций");
    }

    @Test
    public void testSearchLocationWithEmptyInput() {
        locationsPage.searchLocation("");

        List<WebElement> searchResults = locationsPage.getLocationNames();

        Assert.assertTrue(searchResults.size() > 0, "После пустого поиска должны отображаться все локации");
    }

    @Test
    public void testSearchLocationWithPartialInput() {
        String partialLocationName = "Гостевой дом";
        locationsPage.searchLocation(partialLocationName);

        List<WebElement> searchResults = locationsPage.getLocationNames();

        boolean isMatchFound = searchResults.stream()
                .anyMatch(element -> element.getText().contains(partialLocationName));

        Assert.assertTrue(isMatchFound, "Не найдено локации с частичным совпадением для: " + partialLocationName);
    }


}

