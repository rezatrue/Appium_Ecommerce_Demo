package WebBrowser;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Main extends Base{

	public static void main(String[] args) throws MalformedURLException {
		
		AndroidDriver<AndroidElement> driver = capabilities();
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".navbar-toggler")).click();
		driver.findElement(By.cssSelector("a[href*='products']")).click();
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)", "");
		
		List<AndroidElement> list = driver.findElements(By.xpath("//li[@class='list-group-item']"));
		Iterator<AndroidElement> it = list.iterator();
		while(it.hasNext()) {
			AndroidElement e = it.next();
			String product =  e.findElement(By.xpath("./div/div/a")).getText();
			String price = e.findElement(By.xpath("/div/div/div")).getText();
			System.out.println(product + " - " + price);
		}
		
		
	}
}
