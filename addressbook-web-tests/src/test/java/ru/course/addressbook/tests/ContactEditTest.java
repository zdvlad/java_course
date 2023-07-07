package ru.course.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;
import ru.course.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactEditTest extends TestBase {

    @BeforeMethod
    public void ensurePredications()
    {
        if(! app.getContactHelper().isThereContact())
        {
            app.getContactHelper().createContact(new ContactData("Denis", "Zakharov", "9023557076", "z.d.vlad96@mail.ru"));
        }
    }

    @Test
    public void testContactEdit() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size();
        ContactData contactData = new ContactData(before.get(index-1).getId(), "Mark", "Zakharov", "9023557076", "z.d.vlad96@mail.ru");
        app.getContactHelper().editingContact(index, contactData);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index - 1);

        before.add(contactData);
        Comparator<? super ContactData> byId = (x1, x2)->Integer.compare(x1.getId(),x2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
