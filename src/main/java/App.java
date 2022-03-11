import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class App extends Base{

	public static void main(String[] args) throws MalformedURLException {
		System.out.println("Welcome to Appium Ecommerce Demo");
		AndroidDriver<AndroidElement> driver = capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		UserForm form = new UserForm(driver);
		// Form Automation
		form.fillUp();
		// Form Submission failed
		//System.out.println(form.errorMsg());
		
		ProductList productList = new ProductList(driver);
		productList.addToCart("PG 3");
		//productList.addToCart("Converse All Star");
		productList.addToCart("Air Jordan 1 Mid SE");
		productList.openCart();
		
		Cart cart = new Cart(driver);
		cart.calculateAllProductCast();
		System.out.println("App Total: " + cart.getTotalAmount());
		System.out.println("Sum: " + cart.cumulativeSum);
		
	}

}
