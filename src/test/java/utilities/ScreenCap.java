package utilities;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;

import io.appium.java_client.android.AndroidDriver;

public class ScreenCap {
	public static void screenCap(AndroidDriver driver, String name) throws IOException {
		Date d = new Date();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "\\ScreenShot\\" + name + "_" + d.getTime() + ".png");
		Files.copy(src, dest);
	}
}