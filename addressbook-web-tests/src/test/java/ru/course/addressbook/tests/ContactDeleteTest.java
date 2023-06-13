package ru.course.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase{

    @Test
    public void testContactDelete() throws Exception {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().submitContactDeleteByAlert();
        app.getNavigationHelper().openMainPage();
    }
}
