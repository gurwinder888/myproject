package GenericUtilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenersImplementationClass implements ITestListener {

    ExtentTest test;
    
    public ListenersImplementationClass() {
    	BaseClass.getDriver();
    }

    public ListenersImplementationClass(ExtentTest test) {
        this.test = test;
    }

    public void onTestStart(ITestResult result) {
        String testScriptName = result.getMethod().getMethodName();
        System.out.println(testScriptName + " ==== test script execution started ====");

        test = test.createNode(testScriptName); // Assuming ExtentTest test is passed externally
    }

    public void onTestSuccess(ITestResult result) {
        String testScriptName = result.getMethod().getMethodName();
        System.out.println(testScriptName + " ==== Passed ====");

        // Log the success
        test.log(Status.PASS, testScriptName + " == PASS ==");
    }

    public void onTestFailure(ITestResult result) {
        String testScriptName = result.getMethod().getMethodName();
        System.out.println(testScriptName + " ==== Failed ====");

        System.out.println(result.getThrowable());

        // Log for failure
        test.log(Status.FAIL, testScriptName + " == Fail ==");
        test.log(Status.INFO, result.getThrowable());

        // Screenshot - Example of capturing a screenshot
        try {
            String screenShotPath = BaseClass.captureScreenshot(BaseClass.getDriver(), testScriptName);
            test.addScreenCaptureFromPath(screenShotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        String testScriptName = result.getMethod().getMethodName();
        System.out.println(testScriptName + " ==== Skipped ====");

        System.out.println(result.getThrowable());

        // Log for Skip
        test.log(Status.SKIP, testScriptName + " == skipped ==");
        test.log(Status.INFO, result.getThrowable());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        // Not used
    }

    public void onStart(ITestContext context) {
        System.out.println("==== Suite Execution Started ====");
    }

    public void onFinish(ITestContext context) {
        System.out.println("==== Suite Execution Finished ====");
    }
}
