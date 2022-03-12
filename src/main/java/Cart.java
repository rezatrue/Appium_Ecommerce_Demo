import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class Cart {

	private AndroidDriver<AndroidElement> driver;
	
	public Double cumulativeSum = 0.0;
	
	public Cart(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
	public Double getTotalAmount() {
		String amount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		return amountParse(amount);
	}
	
	private Double amountParse(String amount) {
		String removeTxt = "$";
		 return Double.parseDouble(amount.substring(removeTxt.length()).trim());
	}

	private int startX = 0; // endX
	private int startY = 0;
	private int endY = 100;
	private String lastElementName = "";
	
	public void getSwipePoints() {
		try {
			AndroidElement element = driver.findElement(By.xpath("//android.widget.LinearLayout"));
			Point point = element.getLocation();
			
			int x = point.getX();
			int y = point.getY();
			
			Dimension elementSize = element.getSize();
			startY = elementSize.getHeight() + y;
			startX = elementSize.getWidth() / 2 + x;
			endY = y;
			System.out.println(startY +"-"+startX+"-"+y);
		} catch (Exception e) {
			System.out.println("Swipe Points Error");	
		}
	}
	
	private void swipeUp() {
		TouchAction touchAction = new TouchAction(driver);
//		touchAction.press(PointOption.point(startX, 100))
//			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
//			.moveTo(PointOption.point(startX, 50))
//			.release();
//		touchAction.perform();
		String visibleText= "Air Jordan 1 Mid SE";
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))");

	}
	
	public void calculateAllProductCast() {
		driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
		double tempSum = newViewedProductCast();
		cumulativeSum += tempSum;
		getSwipePoints();
		if(startX == 0) return;
		while(tempSum != 0.0) {
			swipeUp();
			tempSum = newViewedProductCast();
			cumulativeSum += tempSum;
		};
				
	}
	
	public Double newViewedProductCast() {
		double sum = 0.0;
		String lastNewElementName = "";
		AndroidElement element = driver.findElement(By.id("com.androidsample.generalstore:id/rvCartProductList"));
		//int count = element.findElements(By.xpath("//android.widget.RelativeLayout")).size();
		int count = element.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		System.out.println("Index : "+ count);
		for(int i = 0; i < count; i++) {
			String txt = element.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			System.out.println(txt);
			lastNewElementName = element.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(lastElementName.equalsIgnoreCase(lastNewElementName)) {
				sum = 0.0; 
			}else {
				sum += amountParse(txt);
			}
		}
		lastElementName = lastNewElementName;
		System.out.println(sum);
		return sum;
	}
	
	public void completePurchase() {

		TouchAction<?> touchAction = new TouchAction<>(driver);
		TapOptions tapOptions = new TapOptions();
		ElementOption elementOption = new ElementOption();
		
		WebElement checkBox = driver.findElement(By.className("android.widget.CheckBox"));
		touchAction.tap(tapOptions.withElement(elementOption.withElement(checkBox))).perform();
		
		WebElement conditions = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		LongPressOptions longPressOptions = new LongPressOptions();
		touchAction.longPress(longPressOptions.withElement(elementOption.withElement(conditions))
				.withDuration(Duration.ofSeconds(2))).release().perform();
		
		
		WebElement closeBtn = driver.findElement(By.xpath("//android.widget.Button[@text='CLOSE']"));
		closeBtn.click();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		WebElement proceedBtn = driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed"));
		proceedBtn.click();
		
	}
	
}
