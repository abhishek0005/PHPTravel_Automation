package com.php.qa.pages;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.php.qa.base.TestBase;

public class HotelPlanPage extends TestBase {
	
	// Object Repository of the Hotel Plan Page
	@FindBy(xpath = "//li[starts-with(@class,'mix stars_0 hotels_amenities_')]")
	List<WebElement> zeroStarHotel;

	@FindBy(xpath = "//li[starts-with(@class,'mix stars_1 hotels_amenities_')]")
	List<WebElement> oneStarHotel;

	@FindBy(xpath = "//li[starts-with(@class,'mix stars_2 hotels_amenities_')]")
	List<WebElement> twoStarHotel;

	@FindBy(xpath = "//li[starts-with(@class,'mix stars_3 hotels_amenities_')]")
	List<WebElement> threeStarHotel;

	@FindBy(xpath = "//li[starts-with(@class,'mix stars_4 hotels_amenities_')]")
	List<WebElement> fourStarHotel;

	@FindBy(xpath = "//li[starts-with(@class,'mix stars_5 hotels_amenities_')]")
	List<WebElement> fiveStarHotel;

	@FindBy(xpath = "//*[@class=\"mixitup-page-stats\"]")
	WebElement hotelsCountDisplayed;

	@FindBy(xpath = "//*[@class='irs irs--round js-irs-0']//*[@class='irs-bar']")
	WebElement priceSlider;

	@FindBy(xpath = "//*[@class=\"irs irs--round js-irs-0\"]/child::span[6]")
	WebElement fromPriceRange;

	@FindBy(xpath = "//*[@class=\"irs irs--round js-irs-0\"]/child::span[7]")
	WebElement toPriceRange;

	@FindBy(xpath = "//*[starts-with(@style,'visibility') and @class='irs-from']")
	WebElement selectedFromRange;

	@FindBy(xpath = "//*[starts-with(@style,'visibility') and @class='irs-to']")
	WebElement selectedToRange;

	@FindBy(xpath = "//li[@style=\"\"]//span[@class=\"price__num\"]")
	List<WebElement> hotelWithinRange;

	@FindBy(xpath = "//*[@for=\"stars_4\"]")
	WebElement count4StarHotel;

	@FindBy(xpath = "//li[starts-with(@class,'mix stars_4 hotels_amenities_')]//child::h3")
	List<WebElement> fourStarHotelName;

	@FindBy(xpath = "//li[starts-with(@class,'mix stars_4 hotels_amenities_')]//child::span[@class='price__num']")
	List<WebElement> fourStarHotelPrice;
	
	@FindBy(xpath="//*[contains(text(),'Search Hotels in dubai')]")
	WebElement seachHotel;

	public List<WebElement> CountAllHotels = new ArrayList<WebElement>();
	public List<WebElement> CountFourStarHotelHotels = new ArrayList<WebElement>();
	public HashMap<String, String> nameWithReviewStar = new HashMap<String, String>();
	public List<WebElement> priceRange = new ArrayList<WebElement>();
	public HashMap<String, String> fourStarHotelNamePrice = new HashMap<>();

	public String hotelsCountDisplayedBelow;
	public String[] hotelsCountDisplayedSplit;
	public String hotelCountValue;
	public double selectedFromPriceRange;
	public double selectedToPriceRange;

	JavascriptExecutor jse = (JavascriptExecutor) driver;
	
	// Initialize the PageFactory and "This" keyword will point to current class object
	public HotelPlanPage() {
		PageFactory.initElements(driver, this);
	}
	
	// Below method is created to count all the stars hotel on the Hotel Plan Page
	public List<WebElement> verifySearchResultCount() {

		CountAllHotels.addAll(fiveStarHotel);
		CountAllHotels.addAll(fourStarHotel);
		CountAllHotels.addAll(threeStarHotel);
		CountAllHotels.addAll(twoStarHotel);
		CountAllHotels.addAll(oneStarHotel);
		CountAllHotels.addAll(zeroStarHotel);

		return CountAllHotels;

	}

	// Below method is created to fetch the total no of hotels displayed at the bottom on the Hotel Plan Page.
	public int countDisplayedBelow() {

		hotelsCountDisplayedBelow = hotelsCountDisplayed.getText();
		hotelsCountDisplayedSplit = hotelsCountDisplayedBelow.split(" ");
		hotelCountValue = hotelsCountDisplayedSplit[4];

		return Integer.parseInt(hotelCountValue);
	}
	
	// Below method is sorting and print the hotel name with review star in descending order (5* to 1*)
	public void hotelNameWithReviewStar(List<WebElement> searchResultCount) {

		for (int i = 0; i < searchResultCount.size(); i++) {

			if (searchResultCount.get(i).getAttribute("class").equalsIgnoreCase("mix stars_0 hotels_amenities_")) {

				nameWithReviewStar.put(searchResultCount.get(i).getAttribute("id"), "0");

			} else if (searchResultCount.get(i).getAttribute("class")
					.equalsIgnoreCase("mix stars_1 hotels_amenities_")) {

				nameWithReviewStar.put(searchResultCount.get(i).getAttribute("id"), "1");

			} else if (searchResultCount.get(i).getAttribute("class")
					.equalsIgnoreCase("mix stars_2 hotels_amenities_")) {

				nameWithReviewStar.put(searchResultCount.get(i).getAttribute("id"), "2");

			} else if (searchResultCount.get(i).getAttribute("class")
					.equalsIgnoreCase("mix stars_3 hotels_amenities_")) {

				nameWithReviewStar.put(searchResultCount.get(i).getAttribute("id"), "3");

			} else if (searchResultCount.get(i).getAttribute("class")
					.equalsIgnoreCase("mix stars_4 hotels_amenities_")) {

				nameWithReviewStar.put(searchResultCount.get(i).getAttribute("id"), "4");

			} else if (searchResultCount.get(i).getAttribute("class")
					.equalsIgnoreCase("mix stars_5 hotels_amenities_")) {

				nameWithReviewStar.put(searchResultCount.get(i).getAttribute("id"), "5");

			}

		}

		LinkedHashMap<String, String> reversenameWithReviewStar = new LinkedHashMap<String, String>();
		nameWithReviewStar.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> reversenameWithReviewStar.put(x.getKey(), x.getValue()));
		
		System.out.println("Sort and Print the hotel name with review star in descending order(5*to 1*)");
		System.out.println(reversenameWithReviewStar);
	}

	// Below method is created to move the slider on the Hotel Plan Page and then count the no of hotel falling in the slider range
	public int sliderBarVerification() throws InterruptedException {
		// TODO Auto-generated method stub
		intializeAction();
		jse.executeScript("window.scrollBy(0,1200)");
		Thread.sleep(5000);
		builder.dragAndDropBy(fromPriceRange, 40, 0).perform();
		Thread.sleep(5000);
		jse.executeScript("window.scrollBy(0,1200)");
		Thread.sleep(5000);
		builder.dragAndDropBy(toPriceRange, -50, 0).perform();

		priceRange.addAll(hotelWithinRange);

		return priceRange.size();
	}

	// Below method is created to count the no of hotels are falling in the slider range
	public int hotelPricesFalling() {

		double selectedFromPriceRange = Double.parseDouble(selectedFromRange.getText());
		double selectedToPriceRange = Double.parseDouble(selectedToRange.getText());
		int count = 0;

		String[] hotelPrices = new String[priceRange.size()];

		for (int i = 0; i < priceRange.size(); i++) {
			hotelPrices[i] = priceRange.get(i).getText().replace("USD", "").trim();
			if (Double.parseDouble(hotelPrices[i]) >= selectedFromPriceRange
					&& Double.parseDouble(hotelPrices[i]) <= selectedToPriceRange) {
				count++;

			}
		}

		return count;

	}
	
	// Below method is created to count the 4 star hotel only and print the same on the console. 
	public void countFourStarhotel()  {
		CountFourStarHotelHotels.addAll(fourStarHotel);
		jse.executeScript("window.scrollBy(0,800)");
		count4StarHotel.click();
		System.out.println("Total no of 4 star Hotels are: " + CountFourStarHotelHotels.size());

	}
	
	// Below method is created to print all the hotel name and price in INR on the console. 
	public void validateHoteNamePriceINR() {

		for (int i = 0; i < fourStarHotelName.size(); i++) {
			fourStarHotelNamePrice.put(fourStarHotelName.get(i).getText(), fourStarHotelPrice.get(i).getText());
		}
		
		System.out.println("Hotel with INR Prices Below: ");
		for (Map.Entry<String, String> entry : fourStarHotelNamePrice.entrySet()) {
			System.out.println("4 Star Hotel Name are "+entry.getKey() + "" + "and Price are: "+entry.getValue());
		}
	}

}
