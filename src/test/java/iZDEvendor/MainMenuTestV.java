package iZDEvendor;

import com.demoqa.entities.iZDEvendor.LoginEntityV;
import com.demoqa.enums.iZDEvendor.EndpointsV;
import com.demoqa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainMenuTestV extends BaseTestV {

    private String getFullUrl(EndpointsV endpoint) {
        return ConfigReader.getValue("baseURLV") + endpoint.getEndpoint();
    }

    @BeforeClass
    public void loginOnce(){
        browserHelper.open(ConfigReader.getValue("baseURLV") + EndpointsV.SIGNIN.getEndpoint());

        LoginEntityV loginEntityV = randomUtilsV.validLoginEntity();
        loginPageV.fillUpLoginForm(loginEntityV);
    }

    @Test
    public void testClickClientsButton() {
        mainMenuPageV.clickBurgerMenuBtn();
        mainMenuPageV.clickClientsBtn();

        webElementActions.verifyPageVendor(getFullUrl(EndpointsV.CLIENTS),
                By.xpath("//h1[text()='Клиенты']"),
                "Клиенты");

        mainMenuPageV.clickLogoBtn();
        webElementActions.assertBaseUrlIsCurrentVendor();
    }

    @Test
    public void testClickObjectsButton() {
        mainMenuPageV.clickBurgerMenuBtn();
        mainMenuPageV.clickMyObjectsBtn();

        webElementActions.verifyPageVendor(getFullUrl(EndpointsV.OBJECTS),
                By.xpath("//div[@class='_navBlock_1qi7k_24']"),
                "Объекты");

        mainMenuPageV.clickLogoBtn();
        webElementActions.assertBaseUrlIsCurrentVendor();
    }

    @Test
    public void testClickSupportButton() {
        mainMenuPageV.clickBurgerMenuBtn();
        mainMenuPageV.clickSupportBtn();

        webElementActions.verifyPageVendor(getFullUrl(EndpointsV.SUPPORT),
                By.xpath("//div[@class='_content_1tdch_18']"),
                "Блок поддержки");

        mainMenuPageV.clickLogoBtn();
        webElementActions.assertBaseUrlIsCurrentVendor();
    }

    @Test
    public void testClickProfileButton() {
        mainMenuPageV.clickProfileBtn();

        webElementActions.verifyPageVendor(getFullUrl(EndpointsV.PROFILE),
                By.xpath("//div[@class='_profile__content_1j4ug_10']"),
                "Блок профиля");

        mainMenuPageV.clickLogoBtn();
        webElementActions.assertBaseUrlIsCurrentVendor();
    }

    @Test
    public void testClickCellarClientsButton() {
        mainMenuPageV.clickCellarClientsBtn();

        webElementActions.verifyPageVendor(getFullUrl(EndpointsV.CLIENTS),
                By.xpath("//h1[text()='Клиенты']"),
                "Клиенты");

        mainMenuPageV.clickLogoBtn();
        webElementActions.assertBaseUrlIsCurrentVendor();
    }

    @Test
    public void testClickCellarMyObjectsButton() {
        mainMenuPageV.clickCellarMyObjectsBtn();

        webElementActions.verifyPageVendor(getFullUrl(EndpointsV.OBJECTS),
                By.xpath("//div[@class='_navBlock_1qi7k_24']"),
                "Объекты");

        mainMenuPageV.clickLogoBtn();
        webElementActions.assertBaseUrlIsCurrentVendor();
    }

    @Test
    public void testClickCellarSupportButton() {
        mainMenuPageV.clickCellarSupportBtn();

        webElementActions.verifyPageVendor(getFullUrl(EndpointsV.SUPPORT),
                By.xpath("//div[@class='_content_1tdch_18']"),
                "Блок поддержки");

        mainMenuPageV.clickLogoBtn();
        webElementActions.assertBaseUrlIsCurrentVendor();
    }

    @Test
    public void testClickAppStoreButton() {
        mainMenuPageV.clickCellarAppStoreBtn();

        browserHelper.switchToNewTab();

        String expectedUrl = "https://apps.apple.com/kg/app/izde-business/id6503256802";
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl,
                "URL открытой вкладки не соответствует ожидаемому. Ожидалось: " + expectedUrl + ", но было: " + currentUrl);

        browserHelper.switchToParentWindowAndCloseChildren();
        webElementActions.assertBaseUrlIsCurrentVendor();
    }
}