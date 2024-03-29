package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.course.addressbook.model.ContactData;
import ru.course.addressbook.model.Contacts;
import ru.course.addressbook.model.GroupData;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

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
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
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

    public void returnToGroupPageWithContact(GroupData group) {
        click(By.linkText(String.format("group page \"%s\"", group.getName())));
    }

    public void returnToMainPage(Properties properties) {
        wd.get(properties.getProperty("web.baseURL"));
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

    public void edit(ContactData contactData, Properties properties) {
        editContactById(contactData);
        fillContactsData(contactData);
        submitEditContact();
        contactsCache = null;
        returnHomePage();
    }

    public void delete(ContactData contactData, Properties properties) {
        selectContactById(contactData);
        deleteContact();
        submitContactDeleteByAlert();
        contactsCache = null;
        returnToMainPage(properties);
    }

    public void inGroup(ContactData contact, GroupData group) {
        selectContactById(contact);
        selectGroup(group, true);
        addContact();
        returnToGroupPageWithContact(group);
    }

    private void addContact() {
        click(By.name("add"));
    }

    public void outGroup(ContactData contact, GroupData group) {
        selectGroup(group, false);
        selectContactById(contact);
        removeContact();
        returnToGroupPageWithContact(group);
    }

    private void removeContact() {
        click(By.name("remove"));
    }

    private void selectGroup(GroupData group, boolean addToGroup) {
        new Select(wd.findElement(By.name(addToGroup ? "to_group" : "group"))).selectByVisibleText(group.getName());
    }

    public Contacts all() {
        if (contactsCache != null)
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

    public boolean hasGroup(ContactData contactData, int groupid) {
        return !contactData.getGroups().stream().filter(g->g.getId() == groupid).collect(Collectors.toSet()).isEmpty();
    }
}
