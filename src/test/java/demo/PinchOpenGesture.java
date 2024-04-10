package demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

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

public class PinchOpenGesture {
	public static void main(String[] args) throws MalformedURLException {
		File f = new File("C:\\Users\\Sunil J\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
		AppiumDriverLocalService server = new AppiumServiceBuilder().withAppiumJS(f).withIPAddress("127.0.0.1")
				.usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
		server.start();

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Lucifer_007");
		dc.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		dc.setCapability("appPackage", "com.hm.goe");
		dc.setCapability("appActivity", "com.hm.goe.app.home.HomeActivity");

		URL url = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(url, dc);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		driver.findElement(By.xpath("//android.widget.TextView[@text='United States/English']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='NO THANKS']")).click();
		driver.findElement(By.id("com.hm.goe:id/action_search_button")).click();
		driver.findElement(By.className("android.widget.ScrollView")).click();
		driver.findElement(By.id("com.hm.goe:id/searchEditText")).sendKeys("Mens Jeans");
		driver.findElement(By.xpath("//android.widget.TextView[@text='mens jeans']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Baggy Jeans']")).click();
		driver.findElement(AppiumBy.accessibilityId("Product Image")).click();
		WebElement image = driver.findElement(By.id("com.hm.goe:id/carouselPhotoView"));

		jse.executeScript("mobile:pinchOpenGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) image).getId(), "percent", 0.75));
	}
}
