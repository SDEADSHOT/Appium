package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Data {
	Properties pr;

	public Data() throws IOException {
		pr = new Properties();
		FileInputStream fis = new FileInputStream("D:\\Code_A\\Frame_h&m\\config.properties");
		pr.load(fis);
	}

	public String getPlatformName() {
		return pr.getProperty("platformName");
	}

	public String getDeviceName() {
		return pr.getProperty("deviceName");
	}

	public String getUDID() {
		return pr.getProperty("udid");
	}

	public String getAppPackage() {
		return pr.getProperty("appPackage");
	}

	public String getAppActivity() {
		return pr.getProperty("appActivity");
	}
}
