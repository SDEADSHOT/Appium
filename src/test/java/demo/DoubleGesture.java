package demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

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

public class DoubleGesture {
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
		dc.setCapability("appPackage", "io.appium.android.apis");
		dc.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

		URL url = new URL("http://localhost:4723");
		AndroidDriver driver = new AndroidDriver(url, dc);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		WebElement views = driver.findElement(AppiumBy.accessibilityId("Views"));
		jse.executeScript("mobile:clickGesture", ImmutableMap.of("elementId", (((RemoteWebElement) views).getId())));
		
		WebElement buttons = driver.findElement(AppiumBy.accessibilityId("Buttons"));
		jse.executeScript("mobile:clickGesture", ImmutableMap.of("elementId", (((RemoteWebElement) buttons).getId())));
		
		WebElement toggle = driver.findElement(AppiumBy.accessibilityId("Toggle"));
		jse.executeScript("mobile:doubleClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) toggle).getId()));
		
		server.stop();
	}
}
