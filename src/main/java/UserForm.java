import org.openqa.selenium.By;

import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class UserForm {

	private AndroidDriver<AndroidElement> driver;
	public UserForm(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
	public void fillUp() {
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Lee");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"China\"))");
		  //   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));     
		driver.findElement(By.xpath("//*[@text=\"China\"]")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
	
	public String errorMsg() {
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Lee");
		//driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"China\"))");
		  //   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));     
		driver.findElement(By.xpath("//*[@text=\"China\"]")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		String msgTxt = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		Assert.assertEquals("Please enter your name", msgTxt);
		
		return msgTxt;
		
		
	}

	
}
