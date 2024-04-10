package demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class ContextSwitch {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		File f = new File("C:\\Users\\Sunil J\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");

		AppiumDriverLocalService server = new AppiumServiceBuilder().withAppiumJS(f).withIPAddress("127.0.0.1")
				.usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();

		server.start();

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Lucifer_007");
		dc.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		dc.setCapability("appPackage", "com.androidsample.generalstore");
		dc.setCapability("appActivity", "com.androidsample.generalstore.MainActivity");

		URL url = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(url, dc);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.xpath("//android.widget.TextView[@text='Afghanistan']")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Cyprus\"));"))
				.click();

		driver.findElement(By.className("android.widget.EditText")).sendKeys("Lucifer");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
		WebElement button = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("mobile:clickGesture", ImmutableMap.of("elementId", (((RemoteWebElement) button).getId())));
		String parentContext = driver.getContext();

		driver.findElement(By.xpath(
				"//android.widget.TextView[@text='Air Jordan 4 Retro']/..//android.widget.TextView[@text='ADD TO CART']"))
				.click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(4000);
		Set<String> contexts = driver.getContextHandles();
		for (String string : contexts) {
			System.out.println(string);
			// driver.context("PackageName");
			driver.context("WEBVIEW_com.androidsample.generalstore");
			String title = driver.getTitle();
			System.out.println(driver.getTitle());
			//break;
		}
		driver.context(parentContext);
	}
}
