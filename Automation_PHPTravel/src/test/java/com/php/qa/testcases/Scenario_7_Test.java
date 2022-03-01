package com.php.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.php.qa.base.TestBase;
import com.php.qa.pages.DashboardPage;
import com.php.qa.pages.HotelPlanPage;
import com.php.qa.pages.LoginPage;
import com.php.qa.pages.SearchPage;

public class Scenario_7_Test extends TestBase {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	SearchPage searchPage;
	HotelPlanPage hotelPlanPage;

	public Scenario_7_Test() {
		super();
	}

	@BeforeClass()
	public void setUp() {

		initialization();
		loginPage = new LoginPage();
	}

	// Count the total 4* Hotels in INR
	@Test(priority = 0)
	public void validate4StarHotel()  {

		loginPage.changeCurrencyoption();
		dashboardPage = loginPage.enterCredentials(prop.getProperty("email"), prop.getProperty("password"));
		searchPage = dashboardPage.navigateToHotelSearchPage();
		hotelPlanPage = searchPage.enterSearchDetails(prop.getProperty("cityName"), prop.getProperty("childAge"),
				prop.getProperty("nationality"), prop.getProperty("Checkin"), prop.getProperty("Checkout"));
		hotelPlanPage.countFourStarhotel();

	}

	// Print Hotel name and price in INR (Indian Rupees)
	@Test(priority = 1)
	public void validateHotelNameAndPrice() {

		hotelPlanPage.validateHoteNamePriceINR();
		System.out.println("Passed----TC_7.Count the total 4* Hotels and print Hotel name and price in INR (Indian Rupees) not in USD is passed----");

	}

	@AfterClass()
	public void tearDown() {
		driver.quit();
	}

}
