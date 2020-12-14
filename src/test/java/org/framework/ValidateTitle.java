package org.framework;

import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.pageObjects.LandingPage;
import org.framework.resources.Base;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ValidateTitle extends Base {

  public RemoteWebDriver driver;
  public static Logger log = LogManager.getLogger(Base.class.getName());

  public ValidateTitle() throws MalformedURLException {
  }

  @BeforeTest
  public void setUp() throws IOException {
    capabilities = initializeDriver();
    driver = new RemoteWebDriver(url, capabilities);
    log.info("Driver is initialized");
    driver.get(props.getProperty("url"));
    log.info("Navigated to home page");
    driver.manage().window().maximize();
    log.info("Windows maximized");
  }

  @Test
  public void titleValidation() {
    LandingPage landingPage = new LandingPage(driver);
    Assert.assertEquals(landingPage.getTitle().getText(), "FEATURED COURSES");
    log.info("Successfully validated the text message");
  }

  @Test
  public void validateMenuItems() {
    LandingPage landingPage = new LandingPage(driver);
    Assert.assertTrue(landingPage.getMenuItems().isDisplayed());
    log.info("Navigation bar is displayed");
  }

  @AfterTest
  public void tearDown() {
    driver.quit();
  }

}
