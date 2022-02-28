package com.php.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.php.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	// Object Repository of the Login Page
	@FindBy(xpath="//*[@name=\"email\" and @placeholder=\"Email\"]")
	WebElement email;
	
	@FindBy(xpath="//span[@id='continue']")
	WebElement continueButton;
	
	@FindBy(xpath="//*[@name=\"password\" and @placeholder=\"Password\"]")
	WebElement passwordInput;
	
	@FindBy(xpath="//*[@class=\"btn btn-default btn-lg btn-block effect ladda-button waves-effect\"]")
	WebElement loginInButton;
	
	@FindBy(xpath="//button[@id=\"currency\"]")
	WebElement currentDropDown;
	
	@FindBy(xpath="//a[@href=\"https://www.phptravels.net/currency-INR\"]")
	WebElement INROption;
	
	@FindBy(xpath="//*[starts-with(@class,'theme-btn') and @href='https://www.phptravels.net/login']")
	WebElement loginInButtons;
	

	// Initialize the PageFactory and "This" keyword will point to current class object
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	// Below method is created to enter the credentials on the Login Page
	public DashboardPage enterCredentials(String userName, String password) {
		
		email.sendKeys(userName);
		passwordInput.sendKeys(password);
		loginInButton.click();
		
		return new DashboardPage();
		
	}
	
	// Below method is created to change the currency from the top navigation and select INR Option.
	public void changeCurrencyoption() {
		
		currentDropDown.click();
		INROption.click();
		loginInButtons.click();
		
	}
	

}
