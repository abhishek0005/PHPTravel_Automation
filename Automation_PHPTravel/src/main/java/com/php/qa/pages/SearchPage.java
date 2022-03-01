package com.php.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.php.qa.base.TestBase;
import com.php.qa.util.TestUtil;

public class SearchPage extends TestBase {

	// Object Repository of the Search Page
	@FindBy(xpath = "//*[@class=\"selection\"]/span")
	WebElement cityNameTextBox;

	@FindBy(xpath = "//*[@class=\"select2-search__field\"]")
	WebElement cityName;

	@FindBy(xpath = "//li[contains(text(),'Dubai,United Arab Emirates')]")
	WebElement dubaiDropDown;

	@FindBy(xpath = "//*[@class=\"btn btn-default btn-lg btn-block effect ladda-button waves-effect\"]")
	WebElement loginInButton;

	@FindBy(xpath = "//*[@name=\"checkin\"]")
	WebElement checkInCalendar;

	@FindBy(xpath = "//div[@class='datepicker dropdown-menu' and contains(@style, 'display: block')]//td")
	List<WebElement> dateEntered;

	@FindBy(xpath = "//*[@class='input-box']")
	WebElement travellers;

	@FindBy(xpath = "//*[@class='roomInc']")
	WebElement room;

	@FindBy(xpath = "//*[@id='adults']/parent::div//div[@class='qtyInc']")
	WebElement adult;

	@FindBy(xpath = "//*[@id='childs']/parent::div//div[@class='qtyInc']")
	WebElement child;

	@FindBy(xpath = "//*[@id=\"ages1\"]")
	WebElement childAge;

	@FindBy(xpath = "//*[@id=\"nationality\"]")
	WebElement nationality;

	@FindBy(xpath = "//*[@id=\"submit\"]")
	WebElement submitButton;

	// Initialize the PageFactory and "This" keyword will point to current class object
	public SearchPage() {
		PageFactory.initElements(driver, this);
	}

	// Below method is created to enter the search details on the Search Page
	public HotelPlanPage enterSearchDetails(String city, String cAge, String nation, String checkIn,
			String checkOut) {

		cityNameTextBox.click();
		cityName.sendKeys(city);
		dubaiDropDown.click();
		checkInCalendar.click();
		TestUtil.dateSelection(dateEntered, checkIn);
		TestUtil.dateSelection(dateEntered, checkOut);
		travellers.click();
		room.click();
		adult.click();
		adult.click();
		adult.click();
		child.click();
		TestUtil.dropdownField(childAge, cAge);
		TestUtil.dropdownField(nationality, nation);
		submitButton.click();

		return new HotelPlanPage();

	}
	
	public void submitButton() {
		submitButton.click();
	}

}
