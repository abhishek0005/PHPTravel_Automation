package com.php.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.php.qa.base.TestBase;

public class DashboardPage extends TestBase {

	// Object Repository of the Dashboard Page
	@FindBy(xpath = "//a[contains(text(),'Hotels')]")
	WebElement hotelsLink;

	// Initialize the PageFactory and "This" keyword will point to current class object
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}

	// Below method is created to click on the Hotel Links on the Dashboard Page
	public SearchPage navigateToHotelSearchPage() {

		hotelsLink.click();

		return new SearchPage();

	}

}
