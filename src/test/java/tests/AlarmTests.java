package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import appiumSetup.CapabilitiesSetup;
import pageObject.AlarmPage;

public class AlarmTests extends CapabilitiesSetup {
	
	@Test
	public void checkAlarmTimeMatch() {
		AlarmPage alarmPage = new AlarmPage(driver);
		String expectedString = "8:30 AM";
		String alarmXpath = "//android.widget.TextView[@content-desc=\"8:30 AM\"]";
		alarmPage.clickAlarm();
		
		String alarmTime = alarmPage.getAlarmDescription(alarmXpath, driver);
		
		Assert.assertEquals( alarmTime,expectedString);
		
		alarmPage.clickAlarm();
		
	}
}
