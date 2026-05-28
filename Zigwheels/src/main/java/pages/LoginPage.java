package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void googleLoginValidation() {

        String parent = driver.getWindowHandle();

        try {
            // Click Login Icon
            WebElement loginIcon = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.id("des_lIcon")
                )
            );
            loginIcon.click();

            // Locate Google button
            WebElement googleBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'googleSignIn')]")
                )
            );

            if (googleBtn.isDisplayed()) {
                System.out.println("\nINFO: Google Login option is available.");
                System.out.println("INFO: Skipping login due to OAuth restriction\n");
            }

        } catch (TimeoutException e) {
            System.out.println("\nWARNING: Login or Google button not found.\n");

        } catch (Exception e) {
            System.out.println("\nERROR: Unexpected issue.");
            System.out.println("Details: " + e.getMessage() + "\n");

        } finally {
            driver.switchTo().window(parent);
        }
    }
}