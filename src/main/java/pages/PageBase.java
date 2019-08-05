package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class PageBase {

  protected WebDriver driver;

  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  public PageBase(WebDriver driver) {
    this.driver = driver;
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public void waitUntilElementClickable(WebElement element, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.elementToBeClickable(element));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  public void waitUntilElementIsVisible(WebElement element, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.visibilityOf(element));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  public void waitUntilElemAttrContainsText(WebElement element, String attribute, String value, int time){
    try {
      new WebDriverWait(driver,time)
              .until(ExpectedConditions.attributeContains(element,attribute,value));
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  public void clickButton(WebElement element) {
    element.click();
  }


  void waitUntilAllElementsVisible (List<WebElement> elements, int time){
    try {
      new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfAllElements(elements));
    } catch (Exception e){
      e.printStackTrace();
    }
  }
}
