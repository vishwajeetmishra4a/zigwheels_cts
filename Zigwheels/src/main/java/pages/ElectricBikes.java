package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.pagefactory.*;
//import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.time.Duration;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class ElectricBikes {
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	public ElectricBikes(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		this.action=new Actions(driver);
	}
	
//	@FindBy(xpath="//span[contains(text(),'MORE')]")
//	WebElement moreBtn;
//	
//	@FindBy(xpath="//a[@data-track-label='nav-electric-vehicles']")
//	WebElement eBike;
	
	public void gotoPage() {
		WebElement moreBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'MORE')]")));
		action.moveToElement(moreBtn).perform();
		
//		wait.until(ExpectedConditions.elementToBeClickable(moreBtn));
//		wait.until(ExpectedConditions.visibilityOf(moreBtn));
//		action.moveToElement(moreBtn).perform();		
		
		WebElement eBike=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-track-label='nav-electric-vehicles']")));
		eBike.click();
	}
	
	public void searchEVBike() {
		
		WebElement Searchbtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class,'c-p')]//strong[@class='block']")));
		Searchbtn.click();
		
		WebElement selectBike=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']//span[@class='icn i-b ev-sprite']")));
		selectBike.click();
	}
	
}
