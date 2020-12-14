package org.framework;

import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.framework.pageObjects.ForgotPassword;
import org.framework.pageObjects.LandingPage;
import org.framework.pageObjects.LoginPage;
import org.framework.resources.Base;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LandingPageTest extends Base {

  public RemoteWebDriver driver;
  public static Logger log = LogManager.getLogger(Base.class.getName());

  public LandingPageTest() throws MalformedURLException {
  }

  @BeforeTest
  public void setUp() throws IOException {
    capabilities = initializeDriver();
    log.info("Driver is initialized");
    driver = new RemoteWebDriver(url, capabilities);
    log.info("Url is set. Capabilities is set.");
  }

  @Test(dataProvider = "getData")
  public void basePageNavigation(String username, String password) throws IOException {
    driver.get(props.getProperty("url"));
    log.info("URL is opened");
    LandingPage landingPage = new LandingPage(driver);
    LoginPage loginPage = landingPage.getLogin();
    log.info("Login button is clicked");

    loginPage.getEmail().sendKeys(username);
    log.info("Username is entered " + username);
    loginPage.getPassword().sendKeys(password);
    log.info("Password is entered " + password);
    loginPage.getSignin().click();
    log.info("Sign in button is clicked");

    landingPage.getLogin();
    log.info("Login button is clicked");
    ForgotPassword forgotPassword = loginPage.forgotPassword();
    forgotPassword.enterEmailAddress();
    log.info("Email address entered");
  }

  @DataProvider
  public Object[][] getData() {

    Object[][] data = new Object[2][2];

    data[0][0] = "abra@abc.com";
    data[0][1] = "123e343r";

    data[1][0] = "plkjd@xyz.com";
    data[1][1] = "massword";

    return data;
  }

  @AfterTest
  public void tearDown() {
    driver.quit();
  }

}

