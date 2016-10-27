package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Natusik on 27.10.2016.
 */
public class NavigationHelper extends BaseHelper {

  public NavigationHelper(ChromeDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  public void goToHomePage() {
      click(By.linkText("home page"));
  }
}
