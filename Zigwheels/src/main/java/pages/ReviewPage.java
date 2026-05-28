package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.*;

public class ReviewPage {

    WebDriver driver;
    WebDriverWait wait;

    public ReviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Navigate to User Reviews
    public void goToUserReviews() {

        // Hover on NEWS & REVIEWS
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'NEWS & REVIEWS')]")));

        new Actions(driver).moveToElement(menu).perform();

        // Click User Reviews
        WebElement userReviews = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-track-label='nav-user-reviews']")));

        userReviews.click();

        // Wait for page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'User Reviews')]")));
    }

    // Get Reviews in Descending Order
    public List<String> getReviewsInDescendingOrder() {

        List<String> reviewList = new ArrayList<>();

        //Locate all review blocks
        List<WebElement> reviews = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'review')]//p | //div[contains(@class,'review')]//h3")));

        for (WebElement review : reviews) {

            String text = review.getText().trim();

            if (!text.isEmpty()) {
            	
                reviewList.add(text);
            }
        }

        // Sort in descending order
        Collections.sort(reviewList, Collections.reverseOrder());

        return reviewList;
    }
    
    public void printCleanReviews(List<String> reviews) {

        System.out.println("\n===== USER REVIEWS (DESCENDING ORDER) =====\n");

        int count = 1;

        for (String review : reviews) {

            // Clean long spaces and junk
            String cleanReview = review.replaceAll("\\s+", " ").trim();

            // Limit size (avoid huge paragraphs)
            if (cleanReview.length() > 200) {
                cleanReview = cleanReview.substring(0, 200) + "...";
            }

            System.out.println("Review " + count + ":");
            System.out.println(cleanReview);
            System.out.println("----------------------------------------");
            
            count++;
        }
    }
}