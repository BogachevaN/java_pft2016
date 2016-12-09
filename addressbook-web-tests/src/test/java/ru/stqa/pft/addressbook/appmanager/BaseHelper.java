package ru.stqa.pft.addressbook.appmanager;

import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.*;

import java.io.File;
import java.util.NoSuchElementException;


/**
 * Created by Natusik on 27.10.2016.
 */
public class BaseHelper {
  protected WebDriver wd;

  public BaseHelper(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    findElement(locator).click();
  }

  protected WebElement findElement(By locator) {
    return wd.findElement(locator);
  }

  protected void type(By locator, String text) {
    WebElement element = findElement(locator);
    String existingText = element.getAttribute("value");
    if (text != null){
      if (! text.equals(existingText)){
        element.clear();
        element.sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {
    WebElement element = findElement(locator);
    if (file != null){
      element.sendKeys(file.getAbsolutePath());
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected void closeAlert() {
    if (isAlertPresent()==true) {
      wd.switchTo().alert().accept();
    }
  }

  protected boolean isCheckBoxSelected(By by) {
    return findElement(by).isSelected();
  }

  protected boolean isElementPresent(By locator) {
    try {
      findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
