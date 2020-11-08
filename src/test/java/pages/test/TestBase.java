package pages.test;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.File;
import java.io.IOException;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static String baseUrl;
  protected static Capabilities capabilities;
  public static final String CITY = "Odessa";

  public EventFiringWebDriver driver;

  public static class MyListener extends
          AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element,
                             WebDriver driver) {
    }

    @Override
    public void afterFindBy(By by, WebElement element,
                            WebDriver driver) {
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      File tmp = ((TakesScreenshot) driver)
              .getScreenshotAs(OutputType.FILE);
      File screen = new File("screen" + System
              .currentTimeMillis() + ".png");
      try {
        Files.copy(tmp, screen);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    System.setProperty("webdriver.gecko.driver", "/home/andi/Downloads/geckodriver");
    driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(capabilities));
    driver.register(new MyListener());
    driver.get(baseUrl);
  }

   @AfterMethod
   public void tearDownMethod(){ driver.quit(); }


  @AfterSuite(alwaysRun = true)
  public void tearDown() { WebDriverPool.DEFAULT.dismissAll(); }
}
