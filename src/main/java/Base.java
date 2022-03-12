import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {
	
	public static AndroidDriver<AndroidElement> capabilities() throws MalformedURLException {
		AndroidDriver<AndroidElement> driver;
		
		DesiredCapabilities cap = new DesiredCapabilities();
		File app = new File("General-Store.apk");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Ali-Pixel-2-XL-API-30");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		
		return driver;
	}

	public static void printContexts(AndroidDriver<AndroidElement> driver) {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		    System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
		}
		//driver.context(contextNames.toArray()[1]); // set context to WEBVIEW_1
	}
	
	
}
