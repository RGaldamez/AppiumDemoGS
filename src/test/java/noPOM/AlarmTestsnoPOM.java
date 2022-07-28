package noPOM;


import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

//EXAMPLE FILE, ALL IN A SINGLE CLASS - Object identification, object interaction and Appium setup. 
//Not ideal for building up tests suites or modularizing tests, Page Object Model is recommended.

public class AlarmTestsnoPOM {
	static AppiumDriver<MobileElement> driver;
	 
//	Remember to update Maven sources for this project before running
//  Test results will appear in 	
	
	@BeforeTest
	public void setup() throws Exception {
		
		
		Reporter.log("Setting up capabilities...",true);
		DesiredCapabilities capabilitites = new DesiredCapabilities() ;
		capabilitites.setCapability("deviceName","Pixel 2" );
		capabilitites.setCapability("udid","emulator-5554" );
		capabilitites.setCapability("platformName","Android" );
		capabilitites.setCapability("platformVersion","11" );
		capabilitites.setCapability("appPackage","com.google.android.deskclock" );
		capabilitites.setCapability("appActivity","com.android.deskclock.DeskClock" );
		URL appiumServerURL = new URL("http://127.0.0.1:4723/wd/hub"); 
		
		driver = new AppiumDriver<MobileElement>(appiumServerURL,capabilitites);
		Reporter.log("Starting tests",true);
		
	}
	
	//Closing the Appium Driver 
	@AfterTest
	public void terminate () {
		Reporter.log("Cleaning up",true);
		if (driver != null) {
			driver.quit();	
		}
		
	}
	
	@Test
	public void alarmTimeValidFormat() {
		MobileElement alarmElement = driver.findElementByXPath("//rk[@content-desc=\"Alarm\"]/android.widget.TextView");
		String expectedString = "8:30 AM";
		alarmElement.click();

		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.ViewGroup[@content-desc=\" Alarm\"])[1]"))); 


		MobileElement alarmDescription=null;
		alarmDescription  = driver.findElementByXPath("//android.widget.TextView[@content-desc=\"8:30 AM\"]");
		
		String alarmTime="";
		if(alarmDescription!=null) {
			alarmTime = alarmDescription.getText();
		}
		
		Assert.assertEquals( alarmTime,expectedString);
		alarmDescription.click();
		
	}
	
	@Test
	public void changeAlarmTime() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TimePicker/android.widget.LinearLayout")));
		
		MobileElement hour9 = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"9\"]"));
		hour9.click();
		
		MobileElement minute25 = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"25\"]"));
		minute25.click();
		
		MobileElement btnOK = driver.findElement(By.id("android:id/button1"));
		btnOK.click();
		
		MobileElement alarmDescription=null;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@content-desc=\"9:25 AM\"]")));
		alarmDescription  = driver.findElementByXPath("//android.widget.TextView[@content-desc=\"9:25 AM\"]");
		
		MobileElement alarmSwitch = driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"9:25 AM Alarm\"]/android.widget.Switch");
		Assert.assertNotNull(alarmDescription.getText(),"9:25 AM");
		Assert.assertEquals(alarmSwitch.getAttribute("checked"),"true");
	}

	@Test
	public void createNewAlarm() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileElement btnAddAlarm = driver.findElementById("com.google.android.deskclock:id/fab");
		btnAddAlarm.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/radial_picker")));
		MobileElement hourPicker = driver.findElementById("android:id/hours");
		MobileElement minutePicker = driver.findElementById("android:id/minutes");
		MobileElement AMpicker = driver.findElementById("android:id/am_label");
		
		String timeOfDayLabel = "";
		
		if(AMpicker.getAttribute("checked")=="true") {
			timeOfDayLabel = "AM";
 		}else {
 			timeOfDayLabel = "PM";
 		}
		
		String currentTime = hourPicker.getText() +":" + minutePicker.getText()+" "+timeOfDayLabel+" Alarm";
		MobileElement btnOK = driver.findElementById("android:id/button1");
		btnOK.click();
		
		Reporter.log("currentTime: "+currentTime,true);
		boolean validAlarmCreated = true;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.deskclock:id/fab")));
		driver.findElementsByAccessibilityId(currentTime);
		
		Assert.assertEquals(validAlarmCreated, true);	
	}
	
	@Test
	public void createNewAlarmCancel() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileElement btnAddAlarm = driver.findElementById("com.google.android.deskclock:id/fab");
		btnAddAlarm.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/radial_picker")));
		MobileElement btnCancel = driver.findElementById("android:id/button2");
		btnCancel.click();
		boolean invalidAlarmCreated = true;
		try {
			MobileElement invalidAlarm = driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\" Alarm\"])[4]");	
		} catch (Exception e) {
			invalidAlarmCreated = false;
		}
		
		Assert.assertEquals(invalidAlarmCreated, false);	
	}
	
	@Test
	public void failingTestExample() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		MobileElement btnAddAlarm = driver.findElementById("com.google.android.deskclock:id/fab");
		btnAddAlarm.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/radial_picker")));
		MobileElement btnCancel = driver.findElementById("android:id/button2");
		btnCancel.click();
		boolean invalidAlarmCreated = true;
		try {
			MobileElement invalidAlarm = driver.findElementByXPath("(//android.view.ViewGroup[@content-desc=\" Alarm\"])[1]");	
		} catch (Exception e) {
			invalidAlarmCreated = false;
		}
		
		Assert.assertEquals(invalidAlarmCreated, false);	
	}

	
	
	
}
