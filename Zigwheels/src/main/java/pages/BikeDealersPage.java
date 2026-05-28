package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class BikeDealersPage {

    WebDriver driver;
    WebDriverWait wait;

    public BikeDealersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ✅ Step 1: Navigate to Bike Dealers
    public void goToBikeDealers() {

        WebElement newBikes = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'NEW BIKES')]")
            )
        );

        new Actions(driver).moveToElement(newBikes).perform();

        WebElement dealers = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@data-track-label='nav-bike-dealers']")
            )
        );

        dealers.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(text(),'Bike Dealers')]")
        ));
    }

    public void searchDealer(String cityName, String brand) {

        // ✅ Enter City
        WebElement cityInput = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Type City Name']")
            )
        );

        cityInput.clear();
        cityInput.sendKeys(cityName);

        // ✅ Select city from suggestion
        WebElement cityOption = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(text(),'" + cityName + "')]")
            )
        );
        cityOption.click();

        // ✅ SELECT MAKE using Select class ✅
        WebElement makeDropdown = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("dealer_make")   // ✅ DIRECT ID
            )
        );

        Select select = new Select(makeDropdown);

        select.selectByVisibleText(brand);   // ✅ Bajaj

        // ✅ Click Search
        WebElement searchBtn = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Search')]")
            )
        );

        searchBtn.click();

        // ✅ Wait for results page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h1[contains(text(),'" + brand + "')]")
        ));
    }

    // ✅ Step 3: Print Dealer Details
    public void printDealerDetails() {

        System.out.println("\n===== BAJAJ BIKE SHOWROOMS IN PUNE =====\n");

        // ✅ Description
        try {
            String description = driver.findElement(By.xpath("//p")).getText();
            System.out.println(description + "\n");
        } catch (Exception e) {
            System.out.println("Description not found\n");
        }

        // ✅ Table Header
        System.out.println("Dealer | Phone No | Address");
        System.out.println("-------------------------------------------------------------");

        // ✅ Table Rows
        List<WebElement> rows = driver.findElements(
            By.xpath("//table//tbody//tr")
        );

        int count = 0;

        for (WebElement row : rows) {

            try {
                String dealer = row.findElement(By.xpath("./td[1]")).getText();
                String phone = row.findElement(By.xpath("./td[2]")).getText();
                String address = row.findElement(By.xpath("./td[3]")).getText();

                System.out.println(dealer + " | " + phone + " | " + address);

                count++;

                // ✅ optional: limit to first 5
                if (count == 5) break;

            } catch (Exception e) {
                // skip bad rows
            }
        }
    }
}
