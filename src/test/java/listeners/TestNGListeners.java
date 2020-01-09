package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("-- Test started -- | " +  iTestResult.getName());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test success! | " +  iTestResult.getName());
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test FAILED! | " +  iTestResult.getName());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test skipped | " +  iTestResult.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("*** All tests finished *** | " +  iTestContext.getName());
    }
}
