package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePredications() {
        if (!app.contact().isThereContact()) {
            app.contact().create(new ContactData().withFirstName("Denis").withSecondName("Zakharov").
                    withPhoneNumber("9023557076").withEmail("z.d.vlad96@mail.ru"));
        }
    }

    @Test
    public void testContactDelete() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData contactData = before.iterator().next();
        app.contact().delete(contactData);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(contactData);
        Assert.assertEquals(before, after);
    }


}
