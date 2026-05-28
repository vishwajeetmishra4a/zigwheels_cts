package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;

public class ElectricCarsPage {

    WebDriver driver;
    WebDriverWait wait;

    public ElectricCarsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Navigate to Electric Cars
    public void goToElectricCars() {

        WebElement newCars = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'NEW CARS')]")));

        new Actions(driver).moveToElement(newCars).perform();

        WebElement electricCars = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-track-label='nav-ev-cars']") ) );

        electricCars.click();

        // wait page load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Electric Cars')]")));
    }

    public void openFirstCar() {

        WebElement firstCar = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//li[contains(@class,'modelItem')]//img)[1]")));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstCar);

        // JS click (VERY IMPORTANT)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstCar);

        // Handle new tab if opened
        for (String win : driver.getWindowHandles()) {
            driver.switchTo().window(win);
        }
    }


    // Extract Car Info
    public void printCarDetails() {

        try {
            String name = driver.findElement(By.xpath("//h1")).getText();

            String price = driver.findElement(By.xpath("//span[contains(text(),'Rs')]")).getText();

            System.out.println("\n===== CAR DETAILS =====");
            System.out.println("Name: " + name);
            System.out.println("Price: " + price);

        } catch (Exception e) {
            System.out.println("Unable to fetch full details");
        }
    }

    // Take Screenshot
    public void takeScreenshot(String fileName) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            File dest = new File("./screenshots/" + fileName + ".png");

            // create folder if not exists
            dest.getParentFile().mkdirs();

            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved: " + dest.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error saving screenshot");
        }
    }
}
