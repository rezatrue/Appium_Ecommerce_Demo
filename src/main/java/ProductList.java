import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ProductList {
	private AndroidDriver<AndroidElement> driver;
	public ProductList(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	private void scrollTo(String name) {
		
		driver
        .findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
        + ".resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView("
        + "new UiSelector().text(\""+name+"\"));");
		
		//................... Second way......................
		//driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + name + "\").instance(0))"));  
		
	}
	
	public void addToCart(String name) {
		scrollTo(name);
		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for(int i = 0; i < count; i++) {
			String text = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(text.equalsIgnoreCase(name)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
		
	}
	
	public void openCart() {
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	}
}
