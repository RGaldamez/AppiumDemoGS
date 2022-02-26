package tests;

import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class Setup {

	static AppiumDriver<MobileElement> driver;
	
	public static void main(String [] args) {

		try {
			setCapabilititesAndStart();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void setCapabilititesAndStart() throws Exception {
		DesiredCapabilities capabilitites = new DesiredCapabilities() ;
//		capabilitites.setCapability("","" );
		capabilitites.setCapability("deviceName","Pixel 2" );
		capabilitites.setCapability("udid","emulator-5554" );
		capabilitites.setCapability("platformName","Android" );
		capabilitites.setCapability("platformVersion","11" );
		capabilitites.setCapability("appPackage","com.google.android.deskclock" );
		capabilitites.setCapability("appActivity","com.android.deskclock.DeskClock" );
		URL url = new URL("http://127.0.0.1:4723/wd/hub"); 
		
		driver = new AppiumDriver<MobileElement>(url,capabilitites);
		MobileElement alarmElement = driver.findElementByXPath("//rk[@content-desc=\"Alarm\"]/android.widget.TextView");
		alarmElement.click();
		
	}
	
}
