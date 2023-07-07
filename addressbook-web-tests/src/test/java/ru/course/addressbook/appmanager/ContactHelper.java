package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.course.addressbook.model.ContactData;
import ru.course.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void editContact(int index) {
        int col = index + 1;//увеличиваем на 1, так как 1-ая строка это заголовки таблицы. Данные начинаются с tr[2]
        click(By.xpath("//table[@id='maintable']/tbody/tr["+col+"]/td[8]/a/img"));
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

    public void createContact(ContactData contactData) {
        initContactCreation();
        fillContactsData(contactData);
        submitContactCreation();
        returnHomePage();
    }

    public void editingContact(int index, ContactData contactData) {
        editContact(index);//передается номер строки, которую редактируем
        fillContactsData(contactData);
        submitEditContact();
        returnToMainPage();
    }

    public void deletingContact(int index) {
        selectContact(index);
        deleteContact();
        submitContactDeleteByAlert();
        returnToMainPage();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contact = new ArrayList<ContactData>();
        /*WebElement table = wd.findElement(By.xpath("//table[@id='maintable']/tbody"));
        int countRows = Integer.parseInt(wd.findElement(By.id("search_count")).getText());
        for (int i = 2; i < countRows + 2; i++) {
            String firstName = table.findElement(By.xpath("//tr[" + i + "]/td[3]")).getText();
            String secondName = table.findElement(By.xpath("//tr[" + i + "]/td[2]")).getText();
            int id = Integer.parseInt(table.findElement(By.xpath("//tr[" + i + "]/td")).findElement(By.tagName("input")).getAttribute("value"));
            ContactData gd = new ContactData(id, firstName, secondName, null, null);
            contact.add(gd);
        }*/

        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for(WebElement element: elements)
        {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String secondName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td")).findElement(By.tagName("input")).getAttribute("value"));
            ContactData gd = new ContactData(id, firstName, secondName, null, null);
            contact.add(gd);
        }
        return contact;
    }
}
