package ru.course.addressbook.tests;


import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

public class ContactEditTest extends TestBase {
    @Test
    public void testContactDelete() throws Exception {
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactsData(new ContactData("Mark", "Zakharov", "9023557076", "z.d.vlad96@mail.ru"));
        app.getContactHelper().submitEditContact();
        app.getNavigationHelper().openMainPage();
    }
}
