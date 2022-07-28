package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlarmPage { 
	
	@AndroidFindBy(xpath="//rk[@content-desc=\"Alarm\"]/android.widget.TextView")
	private MobileElement alarmElement;

	private MobileElement alarmDescription = null;
	private AppiumDriver driver=null;
	
	

	
	public AlarmPage (AppiumDriver driver) {
		this.driver= driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void clickAlarm(){
		alarmElement.click();
	}
	
	public String getAlarmDescription(String alarmXpath, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.ViewGroup[@content-desc=\" Alarm\"])[1]")));
		return ((MobileElement) driver.findElementByXPath(alarmXpath)).getText();
	}
	
	
	
	
}
