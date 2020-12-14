package org.framework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

  static ExtentReports extentReports;

  public static ExtentReports getReportObject() {
    String path = System.getProperty("user.dir") + "/reports/index.html";
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
    sparkReporter.config().setReportName("WebAutomation Results");
    sparkReporter.config().setDocumentTitle("Test Results");

    extentReports = new ExtentReports();
    extentReports.attachReporter(sparkReporter);
    extentReports.setSystemInfo("Tester", "Ravikiran");

    return extentReports;
  }
}
