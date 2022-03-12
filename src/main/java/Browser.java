import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Browser {

	private AndroidDriver<AndroidElement> driver;
	public Browser(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	public void search() {
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//input[@name=\"q\"]")).sendKeys("Hello");
		driver.findElement(By.xpath("//input[@name=\"q\"]")).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
	
}
