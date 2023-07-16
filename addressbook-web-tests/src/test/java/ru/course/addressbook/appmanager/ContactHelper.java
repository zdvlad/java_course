package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.course.addressbook.model.ContactData;
import ru.course.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    private Contacts contactsCache = null;

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
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
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
        contactsCache = null;
        returnHomePage();
    }

    public void edit(ContactData contactData) {
        editContactById(contactData);
        fillContactsData(contactData);
        submitEditContact();
        contactsCache = null;
        returnToMainPage();
    }

    public void delete(ContactData contactData) {
        selectContactById(contactData);
        deleteContact();
        submitContactDeleteByAlert();
        contactsCache = null;
        returnToMainPage();
    }


    public List<ContactData> list() {
        List<ContactData> contact = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String secondName = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td")).findElement(By.tagName("input")).getAttribute("value"));
            contact.add(new ContactData().withId(id).withFirstName(firstName).withLastName(secondName));
        }
        return contact;
    }

    public Contacts all() {
        if(contactsCache != null)
            return new Contacts(contactsCache);

        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String addresses = element.findElement(By.xpath(".//td[4]")).getText();
            String allemails = element.findElement(By.xpath(".//td[5]")).getText();
            String allphones = element.findElement(By.xpath(".//td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td")).findElement(By.tagName("input")).getAttribute("value"));
            contactsCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allphones)
                    .withAddress(addresses)
                    .withAllEmails(allemails));
        }
        return contactsCache;
    }

    public ContactData fromEditForm(ContactData contactData) {
        editContactById(contactData);
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();

        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");

        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        return new ContactData().withId(contactData.getId())
                .withFirstName(firstName)
                .withLastName(lastname)
                .withAddress(address)
                .withMobilePhoneNumber(mobilePhone)
                .withHomePhoneNumber(homePhone)
                .withWorkPhoneNumber(workPhone)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }
}
