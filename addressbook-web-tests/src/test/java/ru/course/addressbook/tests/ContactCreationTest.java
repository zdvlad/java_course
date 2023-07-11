package ru.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;
import ru.course.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contactData = new ContactData().
                withFirstName("Anna").
                withLastName("Valova").
                withMobilePhoneNumber("98764332").
                withEmail("valova@mail.ru");
        app.contact().create(contactData);
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(
                before.withAdded(contactData.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }


}
