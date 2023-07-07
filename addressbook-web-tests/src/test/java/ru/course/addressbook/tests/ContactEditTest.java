package ru.course.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactEditTest extends TestBase {

    @BeforeMethod
    public void ensurePredications()
    {
        if(! app.contact().isThereContact())
        {
            app.contact().create(new ContactData().withFirstName("Denis").withSecondName("Zakharov").
                    withPhoneNumber("9023557076").withEmail("z.d.vlad96@mail.ru"));
        }
    }

    @Test
    public void testContactEdit() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Denis")
                .withSecondName("Zakharov")
                .withPhoneNumber("9023557076")
                .withEmail("z.d.vlad96@mail.ru");
        app.contact().edit(contactData);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contactData);
        Assert.assertEquals(before, after);
    }


}
