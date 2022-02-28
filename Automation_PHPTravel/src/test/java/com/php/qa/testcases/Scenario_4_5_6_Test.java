package com.php.qa.testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.php.qa.base.TestBase;
import com.php.qa.pages.DashboardPage;
import com.php.qa.pages.HotelPlanPage;
import com.php.qa.pages.LoginPage;
import com.php.qa.pages.SearchPage;

public class Scenario_4_5_6_Test extends TestBase {

	DashboardPage dashboardPage;
	LoginPage loginPage;
	SearchPage searchPage;
	HotelPlanPage hotelPlanPage;

	List<WebElement> searchResult;
	int searchResultCount;

	public Scenario_4_5_6_Test() {
		super();
	}

	@BeforeClass()
	public void setUp() {

		initialization();
		loginPage = new LoginPage();
	}

	// Count the search results and validate with number displayed below
	@Test(priority = 0)
	public void countSearchResult() {
		dashboardPage = loginPage.enterCredentials(prop.getProperty("email"), prop.getProperty("password"));
		searchPage = dashboardPage.navigateToHotelSearchPage();
		hotelPlanPage = searchPage.enterSearchDetails(prop.getProperty("cityName"), prop.getProperty("childAge"),
				prop.getProperty("nationality"), prop.getProperty("Checkin"), prop.getProperty("Checkout"));
		searchResult = hotelPlanPage.verifySearchResultCount();
		searchResultCount = searchResult.size();
		int hotelCountDisplayed = hotelPlanPage.countDisplayedBelow();
		Assert.assertEquals(searchResultCount, hotelCountDisplayed);
		System.out.println("Passed----TC_4.Count the search results and validate with number displayed below is passed----");

	}

	// Sort and print the hotels names with review Stars in descending order (5* to 1*)
	@Test(priority = 1)
	public void sortHotelNameWithResult() {

		hotelPlanPage.hotelNameWithReviewStar(searchResult);
		System.out.println("Passed----TC_5.Sort and print the hotels names with review Stars in descending order (5* to 1*) is passed----");

	}

	// Validate the price range sliding bar functionality and Hotels Price are falling under this range
	@Test(priority = 2)
	public void sliderFunctionality() throws InterruptedException {

		int totalHotelInRange = hotelPlanPage.sliderBarVerification();
		int countHotels = hotelPlanPage.hotelPricesFalling();
		Assert.assertEquals(totalHotelInRange, countHotels);
		System.out.println("Passed----TC_6.Validate the price range sliding bar functionality and Hotels Price are falling under this range is passed----");

	}
	
	
	@AfterClass()
	public void tearDown() {
		driver.quit();

	}

}
