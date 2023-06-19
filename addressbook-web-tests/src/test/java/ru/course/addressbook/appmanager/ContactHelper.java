package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.course.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//input[21]"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactsData(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getSecondName());
        type(By.name("mobile"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitContactDeleteByAlert() {
        if(isAlertPresent())
            acceptAlert();
    }

    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitEditContact() {
        click(By.xpath("//input[22]"));
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isThereContact() {
        return isElementExist(By.name("entry"));
    }

    public void createContact(ContactData contactData) {
        initContactCreation();
        fillContactsData(contactData);
        submitContactCreation();
        returnHomePage();
    }
}
