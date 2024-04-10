package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class GestureMethod {
	AndroidDriver driver;
	JavascriptExecutor jse;

	public GestureMethod(AndroidDriver driver) {
		this.driver = driver;
		jse = (JavascriptExecutor) driver;

	}

	public void longPress(WebElement element, int milliseconds) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", milliseconds));
	}

	public void doubleClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));

	}

	public void click(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
	}

	public void dragAndDrop(WebElement element, int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "endX", x, "endY", y));
	}

	public void pinchOpen(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "percent", 0.75));
	}

	public void pinchClose(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "percent", 0.75));
	}

	public void swipeLeftByCoordinates(int startCoordinateHorizontally_x, int widthOfSwipe_x,
			int startCoordinateVertically_y) {
		// Java
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("left", startCoordinateHorizontally_x, "top", startCoordinateVertically_y, "width",
						widthOfSwipe_x, "height", 200, "direction", "left", "percent", 0.75));
	}

	public void swipeRightByCoordinates(int startCoordinateHorizontally_x, int widthOfSwipe_x,
			int startCoordinateVertically_y) {
		// Java
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("left", startCoordinateHorizontally_x, "top", startCoordinateVertically_y, "width",
						widthOfSwipe_x, "height", 200, "direction", "right", "percent", 0.75));
	}

	public void scrollUpByCoordinates(int startCoordinateVertically, int heightOfSwipe) {

	}

	public void scrollDownByCoordinates(int startCoordinateHorizontally, int widthOfSwipe,
			int startCoordinateVertically, int heightOfSwipe) {
	}

	public void swipeByElement(WebElement ele, String direction, double percent) {
		((JavascriptExecutor) driver).executeScript("mobile:swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) ele).getId(), "direction", direction, "percent", percent));
	}

	public void enteringTextAndSearch(WebElement element, String text) {
		element.sendKeys(text);
		driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "search"));
	}
}
