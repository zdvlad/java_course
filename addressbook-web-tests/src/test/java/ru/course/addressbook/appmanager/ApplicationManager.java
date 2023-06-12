package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;


    public void init() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        navigationHelper.openMainPage();
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
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

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
