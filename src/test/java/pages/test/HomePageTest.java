package pages.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageHelper;

public class HomePageTest extends TestBase {
    public HomePageHelper homePage;

    @BeforeMethod
    public void initTests() {
        homePage = PageFactory
                .initElements(driver, HomePageHelper.class);
       homePage.waitUntilHomePageIsLoaded()
               .inputCity(CITY)
               .waitUntilCityListIsLoaded()
               .selectCityFromList()
               .waitUntilDateButtonIsLoaded()
               .selectDays()
               .waitUntilDateButtonIsLoaded()
               .clickSearchButton()
               .waitUntilLowestButtonIsLoaded();
    }

    @Test
    public void lowestFirstButtonTest() {
        homePage.selectLowestFirstButtonFirst()
                .waitUntilResultsPageIsLoaded();
        AssertionError.class.getCanonicalName();
        Assert.assertTrue(homePage.sortPricesIncreasingSucccess());
    }
}
