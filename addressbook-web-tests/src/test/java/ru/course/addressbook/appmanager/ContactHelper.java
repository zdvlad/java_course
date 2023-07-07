package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.course.addressbook.model.ContactData;
import ru.course.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitContactDeleteByAlert() {
        if (isAlertPresent())
            acceptAlert();
    }

    private void editContactById(ContactData contactData) {
        click(By.xpath("//table[@id='maintable']/tbody/tr/td/a[@href='edit.php?id=" + contactData.getId() + "']"));
        //click(By.xpath("//table[@id='maintable']/tbody/tr[" + col + "]/td[8]/a/img"));
    }

    private void selectContactById(ContactData contactData) {
        click(By.xpath("//table[@id='maintable']/tbody/tr/td/input[@value='" + contactData.getId() + "']"));
    }

    public void submitEditContact() {
        click(By.xpath("//input[22]"));
    }

    public void returnHomePage() {
        click(By.linkText("home page"));
    }

    public void returnToMainPage() {
        open("http://localhost/addressbook/");
    }

    public boolean isThereContact() {
        return isElementExist(By.name("entry"));
    }

    public void create(ContactData contactData) {
        initContactCreation();
        fillContactsData(contactData);
        submitContactCreation();
        returnHomePage();
    }

    public void edit(ContactData contactData) {
        editContactById(contactData);//передается номер строки, которую редактируем
        fillContactsData(contactData);
        submitEditContact();
        returnToMainPage();
    }

    public void delete(ContactData contactData) {
        selectContactById(contactData);
        deleteContact();
        submitContactDeleteByAlert();
        returnToMainPage();
    }


    public List<ContactData> list() {
        List<ContactData> contact = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String secondName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td")).findElement(By.tagName("input")).getAttribute("value"));
            contact.add(new ContactData().withId(id).withFirstName(firstName).withSecondName(secondName));
        }
        return contact;
    }

    public Set<ContactData> all() {
        Set<ContactData> contact = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String secondName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td")).findElement(By.tagName("input")).getAttribute("value"));
            contact.add(new ContactData().withId(id).withFirstName(firstName).withSecondName(secondName));
        }
        return contact;
    }

}
