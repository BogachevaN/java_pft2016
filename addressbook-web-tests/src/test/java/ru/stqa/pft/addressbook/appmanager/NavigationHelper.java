package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Natusik on 27.10.2016.
 */
public class NavigationHelper {
  private ChromeDriver wd;

  public NavigationHelper(ChromeDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void goToHomePage() {
      wd.findElement(By.linkText("home page")).click();
  }
}
