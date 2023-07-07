package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTest extends TestBase{

    @BeforeMethod
    public void ensurePredications()
    {
        if(! app.getContactHelper().isThereContact())
        {
            app.getContactHelper().createContact(new ContactData("Denis", "Zakharov", "9023557076", "z.d.vlad96@mail.ru"));
        }
    }

    @Test
    public void testContactDelete() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().deletingContact(index);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
