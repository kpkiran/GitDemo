package org.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import java.io.IOException;
import java.net.MalformedURLException;
import org.framework.resources.Base;
import org.framework.resources.ExtentReportNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends Base implements ITestListener {

  ExtentReports extentReports = ExtentReportNG.getReportObject();
  ExtentTest test;
  ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

  public Listeners() throws MalformedURLException {
  }

  @Override
  public void onTestStart(ITestResult iTestResult) {
    test = extentReports.createTest(iTestResult.getMethod().getMethodName());
    threadLocal.set(test);
  }

  @Override
  public void onTestSuccess(ITestResult iTestResult) {
    threadLocal.get().log(Status.PASS, "Test Passed");
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    threadLocal.get().fail(iTestResult.getThrowable());
    WebDriver driver = null;
    String testCaseName = iTestResult.getMethod().getMethodName();
    try {
      driver = (WebDriver)iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
    try {
        threadLocal.get().addScreenCaptureFromPath(getScreenShot(testCaseName, driver), iTestResult.getMethod().getMethodName());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {

  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

  }

  @Override
  public void onStart(ITestContext iTestContext) {

  }

  @Override
  public void onFinish(ITestContext iTestContext) {
    extentReports.flush();
  }
}
