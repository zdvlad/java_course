package ru.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

public class ContactDeleteTest extends TestBase{

    @Test
    public void testContactDelete() throws Exception {
        if(! app.getContactHelper().isThereContact())
        {
            app.getContactHelper().createContact(new ContactData("Denis", "Zakharov", "9023557076", "z.d.vlad96@mail.ru"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().submitContactDeleteByAlert();
        app.getNavigationHelper().openMainPage();
    }
}
