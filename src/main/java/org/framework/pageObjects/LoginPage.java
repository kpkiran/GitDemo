package org.framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

  public WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  By email = By.xpath("//input[@id='user_email']");
  By password = By.xpath("//input[@id='user_password']");
  By signin = By.xpath("//input[@value='Log In']");
  By forgotPassword = By.className(".link-below-button");

  public WebElement getEmail() {
    return driver.findElement(email);
  }

  public WebElement getPassword() {
    return driver.findElement(password);
  }

  public WebElement getSignin() {
    return driver.findElement(signin);
  }

  public ForgotPassword forgotPassword() {
    driver.findElement(forgotPassword).click();
    ForgotPassword forgotPassword = new ForgotPassword(driver);
    return forgotPassword;
  }

}
