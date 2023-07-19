package ru.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;
import ru.course.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePredications() {
        if (!app.contact().isThereContact()) {
            app.contact().create(new ContactData().withFirstName("Denis").withLastName("Zakharov").
                    withMobilePhoneNumber("9023557076").withEmail("z.d.vlad96@mail.ru"));
        }
    }

    @Test
    public void testContactDelete() throws Exception {
        Contacts before = app.db().contacts();
        ContactData contactData = before.iterator().next();
        app.contact().delete(contactData, app.getProperties());
        Contacts after = app.db().contacts();

        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(contactData)));
    }
}
