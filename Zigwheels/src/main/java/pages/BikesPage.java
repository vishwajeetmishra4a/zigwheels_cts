package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BikesPage {

	WebDriver driver;

	public BikesPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void goToUpcomingBikes() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    handlePopup();

	    WebElement newBikes = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//span[normalize-space()='NEW BIKES']")));

	    // Scroll to element
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newBikes);

	    // Strong hover
	    Actions actions = new Actions(driver);
	    actions.moveToElement(newBikes)
	           .pause(Duration.ofSeconds(2))
	           .moveToElement(newBikes)
	           .perform();

	    // DIRECTLY wait for clickable Upcoming (no UL wait)
	    WebElement upcoming = wait.until(
	        ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[@data-track-label='nav-upcoming-bikes']")));

	    // Use JS click (best reliability)
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", upcoming);
	}

	public void selectHondaManufacturer() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // Wait for page to load
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Upcoming Bikes')]")));

	    // Locate Honda brand
	    WebElement honda = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-track-label='filter-by-brand' and contains(text(),'Honda')]")));

	    // Scroll into view (VERY IMPORTANT)
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", honda);

	    // Click using JS (most reliable)
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", honda);
	}
	
	
	public void getHondaBikesUnder4L() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    // Wait for bikes to load
	    List<WebElement> bikes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li[contains(@class,'modelItem') and not(contains(@class,'hidden'))]")));

	    for (WebElement bike : bikes) {

	        try {
	            // Bike Name
	            String bikeName = bike.findElement(By.xpath(".//strong")).getText();

	            // Price (using attribute - BEST)
	            String priceValue = bike.getAttribute("data-price");

	            double priceLakh = Double.parseDouble(priceValue) / 100000;

	            // Launch Date
	            String launchDate = bike.findElement(By.xpath(".//div[contains(text(),'Expected')]")).getText();

	            // Filter < 4L
	            if (priceLakh > 0 && priceLakh < 4.0) {

	                System.out.println("Bike Name: " + bikeName);
	                System.out.println("Price: Rs " + priceLakh + " Lakh");
	                System.out.println("Launch Date: " + launchDate);
	                System.out.println("----------------------------");
	            }

	        } catch (Exception e) {
	            System.out.println("Error reading bike data");
	        }
	    }
	}

	public void handlePopup() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='close']")));
	        closeBtn.click();
	    } catch (Exception e) {
	        System.out.println("No popup found");
	    }
	}
}
