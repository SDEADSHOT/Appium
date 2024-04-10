package utilities;

import java.util.Set;

import org.openqa.selenium.devtools.v112.emulation.model.ScreenOrientation;

import io.appium.java_client.android.AndroidDriver;

public class DriverMethod {
	AndroidDriver driver;
	org.openqa.selenium.ScreenOrientation screen;

	public DriverMethod(AndroidDriver driver) {
		this.driver = driver;
		screen = driver.getOrientation();
	}

	public void installingApplication(String path) {
		driver.installApp(path);
	}

	public void checkingIfAppIsInstalled(String packageName) {
		driver.isAppInstalled(packageName);
	}

	public void uninstallingApplication(String packageName) {
		driver.removeApp(packageName);
	}

	public void isDeviceLocked() {
		driver.isDeviceLocked();
	}

	public void landscapeMode() {
		driver.rotate(screen.LANDSCAPE);
	}

	public void portraitMode() {
		driver.rotate(screen.PORTRAIT);
	}

	public void enteringUrl(String url) {
		driver.get(url);
	}

	public void switchingToContext() {
		// No Implementation
	}
}
