package org.framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPassword {

  public WebDriver driver;

  public ForgotPassword(WebDriver driver) {
    this.driver = driver;
  }

  By emailAddress = By.id("user_email");
  By submitButton = By.className(".btn.btn-primary.btn-md");

  public void enterEmailAddress() {
    driver.findElement(emailAddress).sendKeys("tyy@mmm.com");
  }

  public void submitEmailAddress() {
    driver.findElement(submitButton).click();
  }

}
