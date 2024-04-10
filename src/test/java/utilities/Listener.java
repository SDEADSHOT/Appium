package utilities;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.hm.testcase.BaseClass;

public class Listener extends BaseClass implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				ScreenCap.screenCap(driver, result.getMethod().getMethodName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Screen Shot taken for " + result.getMethod().getMethodName());
	}
}