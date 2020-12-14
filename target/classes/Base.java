package org.framework.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Base {

  public RemoteWebDriver driver;
  public Properties props;
  public URL url = new URL("http://localhost:4444/wd/hub");
  public DesiredCapabilities capabilities;


  public Base() throws MalformedURLException {
  }

  public DesiredCapabilities initializeDriver() throws IOException {
    props = new Properties();
    FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/java/org/framework/resources/config.properties"));
    props.load(fis);

    String browserName = props.getProperty("browser");
    System.out.println(browserName);

    if (browserName.equalsIgnoreCase("chrome")) {
//      System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
//      driver = new ChromeDriver();
      capabilities = DesiredCapabilities.chrome();
      capabilities.acceptInsecureCerts();

    } else if (browserName.equalsIgnoreCase("firefox")) {
//      driver = new FirefoxDriver();
      capabilities = DesiredCapabilities.firefox();

    };

//    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    driver.manage().window().maximize();
//    driver.manage().deleteAllCookies();
    return capabilities;
  }

  public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
    File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
    String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
    FileUtils.copyFile(source, new File(destinationFile));

    return destinationFile;
  }
}