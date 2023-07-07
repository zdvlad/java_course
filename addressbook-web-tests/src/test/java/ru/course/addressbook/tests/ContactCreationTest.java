package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData contactData = new ContactData().
                withFirstName("Anna").
                withSecondName("Valova").
                withPhoneNumber("98764332").
                withEmail("valova@mail.ru");
        app.contact().create(contactData);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contactData.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt());
        before.add(contactData);
        Assert.assertEquals(before, after);
    }


}
