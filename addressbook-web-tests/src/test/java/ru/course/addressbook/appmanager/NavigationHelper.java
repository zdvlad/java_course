package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void openMainPage() {
        open("http://localhost/addressbook/");
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }


    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

}
