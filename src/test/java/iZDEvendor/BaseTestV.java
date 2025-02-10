package iZDEvendor;

import com.demoqa.drivers.DriverManager;
import com.demoqa.helpers.AlertHelper;
import com.demoqa.helpers.BrowserHelper;
import com.demoqa.helpers.IframeHelper;
import com.demoqa.helpers.WebElementActions;
import com.demoqa.listener.ScreenshotListener;
import com.demoqa.pages.iZDEvendor.CreateObjectPage.AddPicturePage;
import com.demoqa.pages.iZDEvendor.CreateObjectPage.DetailObjectPage;
import com.demoqa.pages.iZDEvendor.CreateObjectPage.FacilityDescriptionPage;
import com.demoqa.pages.iZDEvendor.CreateObjectPage.RegulationsPage;
import com.demoqa.pages.iZDEvendor.LocationsPageV;
import com.demoqa.pages.iZDEvendor.LoginPageV;
import com.demoqa.pages.iZDEvendor.MainMenuPageV;
import com.demoqa.utils.iZDEvendor.RandomUtilsV;
import iZDEvendor.CreateObjectsTest.FacilityDescriptionTest;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v120.page.Page;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({AllureTestNg.class, ScreenshotListener.class})

public class BaseTestV {

    private static final Logger log = LoggerFactory.getLogger(BaseTestV.class);
    protected WebDriver driver;
    protected RandomUtilsV randomUtilsV;
    protected WebElementActions webElementActions;
    protected WebDriverWait wait;

    protected AlertHelper alertHelper;
    protected BrowserHelper browserHelper;
    protected IframeHelper iframeHelper;

    protected MainMenuPageV mainMenuPageV;
    protected LoginPageV loginPageV;
    protected LocationsPageV locationsPage;
    protected FacilityDescriptionPage facilityDescriptionPage;
    protected DetailObjectPage detailObjectPage;
    protected AddPicturePage addPicturePage;
    protected RegulationsPage regulationsPage;

    protected FacilityDescriptionTest facilityDescriptionTest;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        driver = DriverManager.getDriver();
        randomUtilsV = new RandomUtilsV();
        webElementActions = new WebElementActions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        alertHelper = new AlertHelper(driver);
        browserHelper = new BrowserHelper(driver);
        iframeHelper = new IframeHelper(driver);

        mainMenuPageV = new MainMenuPageV();
        loginPageV = new LoginPageV();
        locationsPage = new LocationsPageV();
        facilityDescriptionPage = new FacilityDescriptionPage();
        detailObjectPage = new DetailObjectPage();
        addPicturePage = new AddPicturePage();
        regulationsPage = new RegulationsPage();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        DriverManager.closeDriver();
    }
}
