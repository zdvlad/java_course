package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.List;

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
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
