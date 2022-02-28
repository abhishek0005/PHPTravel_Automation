package com.php.qa.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.php.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICT_WAIT = 20;
	
	// Below method is created to enter value in the Drop Down
	public static void dropdownField(WebElement element, String input) {
		Select value = new Select(element);
		value.selectByValue(input);
	}
	
	// Below method is created to select the date from the date picker
	public static void dateSelection(List<WebElement> checkInInputDate, String dateEntered) {
		String[] dateEnteredSplit = dateEntered.split("-");
		int dateEnteredAfterSplit = Integer.parseInt(dateEnteredSplit[0]);
		for (int i = 0; i < checkInInputDate.size(); i++) {
			String date = checkInInputDate.get(i).getText();
			int dateFromCount = Integer.parseInt(date);
			if (dateFromCount == dateEnteredAfterSplit) {
				checkInInputDate.get(i).click();
				break;

			}
		}

	}

	public static void takeScreenshot(String name) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile,
				new File(currentDir + "/screenshots/" + name + System.currentTimeMillis() + ".png"));
	}

}
