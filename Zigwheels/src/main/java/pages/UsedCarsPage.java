package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UsedCarsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By carCardsLocator = By.xpath("//div[contains(@class, 'gsc_col-md-12') or contains(@class, 'gsc_col-sm-12')]//div[contains(@class, 'b2c-card')]");
    private final By carNameLocator = By.xpath(".//h3[contains(@class, 'gsc_col-xs-12') or a]");
    private final By carPriceLocator = By.xpath(".//div[contains(text(),'Rs.') or contains(@class,'price')]");

    public UsedCarsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void goToUsedCarsChennai() {
        // Hover MORE
        WebElement more = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='MORE']"))
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", more);

        new Actions(driver).moveToElement(more)
                .pause(Duration.ofSeconds(2))
                .perform();

        // Click Used Cars
        WebElement usedCars = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-track-label='nav-used-car']"))
        );
        usedCars.click();

        // Wait for popup and select Chennai (Updated to Chennai matching your method name intent)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city-popup-body")));

        WebElement city = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@data-city_name,'Chennai')]"))
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", city);

        new Actions(driver)
                .moveToElement(city)
                .pause(Duration.ofSeconds(1))
                .click()
                .perform();

        // Wait for car result cards to fully render instead of the old 'zw-sr-secLev' list
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(carCardsLocator));
    }

    // Extract Popular Models from the visible search results
    public List<String> getPopularModels() {
        List<String> carList = new ArrayList<>();

        // Locate all available car result cards
        List<WebElement> cars = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(carCardsLocator)
        );

        for (WebElement car : cars) {
            try {
                // Extract Car Name (e.g., "Maruti Alto 800 VXI BSVI")
                String name = car.findElement(carNameLocator).getText().trim();

                // Extract Price (e.g., "Rs. 2.89 Lakh")
                String price = car.findElement(carPriceLocator).getText().trim();

                if (!name.isEmpty()) {
                    carList.add(name + " - " + price);
                }
            } catch (NoSuchElementException e) {
                // Safely skip structure variations or ad-banners inside the grid
            }
        }
        return carList;
    }
}