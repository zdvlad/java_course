package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase{

    @Test
    public void testContactDelete() throws Exception {
        if(! app.getContactHelper().isThereContact())
        {
            app.getContactHelper().createContact(new ContactData("Denis", "Zakharov", "9023557076", "z.d.vlad96@mail.ru"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().submitContactDeleteByAlert();
        app.getNavigationHelper().openMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
