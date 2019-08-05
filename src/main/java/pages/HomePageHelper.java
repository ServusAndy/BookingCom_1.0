package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Sample page
 */
public class HomePageHelper extends PageBase {

    @FindBy(id = "ss")
    WebElement inputCityField;

    @FindBy(className = "search_hl_name")
    WebElement citysList;

    @FindBy(xpath = "//div[contains(@class,'xp__dates-inner xp__dates__checkin')]")
    WebElement datesCheckButton;

    @FindBy(xpath = "//td[@class='bui-calendar__date']")
    List<WebElement> calDates;

    @FindBy(className = "xp__button")
    WebElement searchButton;

    @FindBy(xpath = "//div[@id='sr_notice_no_dorms']")
    WebElement roomsInfoField;

    @FindBy(xpath = "//a[contains(text(),'Price (lowest first)')]")
    WebElement lowestFirstButton;

    @FindBy(xpath = "//div[contains(@class, 'bui-price-display__value')]")
    List<WebElement> allPrices;

    public HomePageHelper(WebDriver webDriver) {
        super(webDriver);
    }


    public HomePageHelper waitUntilHomePageIsLoaded() {
        waitUntilElementClickable(datesCheckButton, 30);
        return this;
    }


    public HomePageHelper clickSearchButton() {
        clickButton(searchButton);
        return this;
    }

    public HomePageHelper inputCity(String city) {
        inputCityField.sendKeys(city);
        return this;
    }

    public HomePageHelper waitUntilCityListIsLoaded() {
        waitUntilElementIsVisible(citysList, 30);
        return this;
    }

    public HomePageHelper selectCityFromList() {
        clickButton(citysList);
        return this;
    }

    public HomePageHelper waitUntilDateButtonIsLoaded() {
        waitUntilElementClickable(datesCheckButton, 30);
        return this;
    }

    public HomePageHelper waitUntilResultsPageIsLoaded() {
        //waitUntilElementIsVisible(roomsInfoField, 30);
        waitUntilElemAttrContainsText(roomsInfoField,"class",
                "small_warning small_warning--highlighted",3);
        return this;
    }

    public void waitUntilLowestButtonIsLoaded() {
        waitUntilElementClickable(lowestFirstButton,30);
    }

    public HomePageHelper selectLowestFirstButtonFirst() {
        clickButton(lowestFirstButton);
        return this;
    }

    public HomePageHelper selectDays() {
        LocalDate today = LocalDate.now();
        LocalDate week = today.plusDays(7L);
        String data = week.toString();
        calDates.stream()
                .filter(el -> el.getAttribute("data-date").contains(data))
                .findFirst().ifPresent(el -> el.click());
        return this;
    }


    public boolean sortPricesIncreasingSucccess(){
        int size = allPrices.size();
        int[] arrPrices = new int[size];
        for(int i = 0;i<size; i++){
            arrPrices[i] = parseInt(allPrices.get(i).getText().substring(2));
        }

        int[] copiedArrPrices = Arrays.copyOf(arrPrices,size);
        selectionSort(copiedArrPrices);

        return Arrays.equals(copiedArrPrices,arrPrices);
    }

    public static void selectionSort(int[] arr){
        int tmp;
        for(int i =0;i<arr.length;i++){
            int min = arr[i];
            int min_i = i;
            for(int j = i+1;j<arr.length;j++){
                if(arr[j]< min){
                    min = arr[j];
                    min_i = j;
                }

            }
            if(i != min_i){
                tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
    }


}


