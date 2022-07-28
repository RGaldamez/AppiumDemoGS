package appiumSetup;

import java.net.URL;
import java.util.concurrent.TimeUnit;

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

public class CapabilitiesSetup {

	protected static AppiumDriver<MobileElement> driver;
	 
//	Remember to update Maven sources for this project before running and to update dependencies if needed, make sure they are compatible with each other. 
//	Check pom.xml to update dependencies and add others.
//  Test results will appear in test-Output folder
	
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Starting tests",true);
	
	}
	
	//Closing the Appium Driver
	@AfterTest
	public void terminate () {
		//Use the Reporter.log to log desired output into the console
		Reporter.log("Cleaning up",true);
		if (driver != null) {
			driver.quit();	
		}
		
	}
}
