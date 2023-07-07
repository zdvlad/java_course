package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.contact().list();
        ContactData contactData = new ContactData().
                withFirstName("Anna").
                withSecondName("Valova").
                withPhoneNumber("98764332").
                withEmail("valova@mail.ru");
        app.contact().create(contactData);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        contactData.withId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
        before.add(contactData);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
