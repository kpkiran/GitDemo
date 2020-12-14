package org.framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LandingPage {

  public RemoteWebDriver driver;

  By signin = By.cssSelector("a[href*='sign_in']");
  By title = By.cssSelector(".text-center>h2");
  By menuitems = By.cssSelector(".nav.navbar-nav.navbar-right");

  public LandingPage(RemoteWebDriver driver) {
    this.driver = driver;
  }

  public LoginPage getLogin() {
    driver.findElement(signin).click();
    LoginPage loginPage = new LoginPage(driver);
    return loginPage;
  }

  public WebElement getTitle() {
    return driver.findElement(title);
  }

  public WebElement getMenuItems() {
    return driver.findElement(menuitems);
  }
}
