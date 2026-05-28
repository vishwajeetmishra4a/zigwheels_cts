package tests;

import java.util.List;

import org.testng.annotations.Test;
import base.BaseTest;
import pages.BikeDealersPage;
import pages.BikesPage;
import pages.ElectricCarsPage;
import pages.LoginPage;
import pages.ReviewPage;
import pages.UsedCarsPage;
import pages.ElectricBikes;
import utils.ScreenshotUtil;


public class ZigwheelsTest extends BaseTest {
	
	@Test(enabled=true)
	public void testMorePage() {
		ElectricBikes electricpage=new ElectricBikes(driver);
		
		electricpage.gotoPage();
		electricpage.searchEVBike();
	}

	@Test(priority = 1, enabled = false)
	public void testUpcomingHondaBikes() {

		BikesPage bikesPage = new BikesPage(driver);

		bikesPage.goToUpcomingBikes();        // Step 1: Navigate
		bikesPage.selectHondaManufacturer();  // Step 2: Filter Honda
		bikesPage.getHondaBikesUnder4L();     // Step 3: Extract data
	}

	@Test(priority = 2 ,enabled = false)
	public void testUsedCarsChennai() {

		UsedCarsPage page = new UsedCarsPage(driver);

		page.goToUsedCarsChennai();

		List<String> cars = page.getPopularModels();

		for (String car : cars) {
			System.out.println(car);
		}
	}

	@Test(enabled = false)
	public void testGoogleLogin() {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.googleLoginValidation();

		System.out.println("✅ Google Login Test Completed");
	}

	@Test(enabled = false)
	public void testUserReviews() {

		ReviewPage reviewPage = new ReviewPage(driver);

		reviewPage.goToUserReviews();

		List<String> reviews = reviewPage.getReviewsInDescendingOrder();

		reviewPage.printCleanReviews(reviews);
		
		ScreenshotUtil.capture(driver, "review");
	}

	@Test(enabled = false)
	public void testElectricCars() {

		ElectricCarsPage page = new ElectricCarsPage(driver);

		page.goToElectricCars();
		page.openFirstCar();
		page.printCarDetails();

		//take screenshot
		ScreenshotUtil.capture(driver, "ElectricCar");
	}

	@Test(enabled = false)
	public void testBikeDealers() {

		BikeDealersPage page = new BikeDealersPage(driver);
		
		page.goToBikeDealers();

		page.searchDealer("Pune", "Bajaj");
	}

}

