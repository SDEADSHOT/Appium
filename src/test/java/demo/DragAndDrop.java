package demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DragAndDrop {
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
		dc.setCapability("appPackage", "io.appium.android.apis");
		dc.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

		URL url = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(url, dc);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		TouchAction action = new TouchAction(driver);

		WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
		jse.executeScript("mobile:clickGesture", ImmutableMap.of("elementId", (((RemoteWebElement) views).getId())));

		WebElement drag_and_drop = driver.findElement(AppiumBy.accessibilityId("Drag and Drop"));
		jse.executeScript("mobile:clickGesture",
				ImmutableMap.of("elementId", (((RemoteWebElement) drag_and_drop).getId())));

		WebElement src = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));
		WebElement dest = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));
		Thread.sleep(4000);

		jse.executeScript("mobile:dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) src).getId(), "endX", 620, "endY", 581));
		
		System.out.println(driver.findElement(By.xpath("//android.widget.TextView[@text='Dropped!']")).isDisplayed());
		server.stop();
	}
}
