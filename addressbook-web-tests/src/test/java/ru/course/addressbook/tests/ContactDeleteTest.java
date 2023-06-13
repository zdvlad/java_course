package ru.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

public class ContactDeleteTest extends TestBase{

    @Test
    public void testContactDelete() throws Exception {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().submitContactDeleteByAlert();
        app.getNavigationHelper().openMainPage();
    }
}
