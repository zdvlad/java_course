package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.course.addressbook.model.ContactData;

public class ContactHelper {
    private WebDriver wd;

    public ContactHelper(WebDriver wd)
    {
        this.wd = wd;
    }

    public void submitContactCreation() {
      wd.findElement(By.xpath("//input[21]")).click();
    }

    public void initContactCreation() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void fillContactsData(ContactData contactData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactData.getSecondName());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(contactData.getPhoneNumber());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }
}
