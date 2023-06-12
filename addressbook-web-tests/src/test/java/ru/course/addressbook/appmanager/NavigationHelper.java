package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    protected void openMainPage() {
        wd.get("http://localhost/addressbook/");
    }

    public void returnHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }


    public void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

}
