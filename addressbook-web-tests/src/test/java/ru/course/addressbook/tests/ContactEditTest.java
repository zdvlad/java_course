package ru.course.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;
import ru.course.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactEditTest extends TestBase {

    @BeforeMethod
    public void ensurePredications()
    {
        if(! app.contact().isThereContact())
        {
            app.contact().create(new ContactData().withFirstName("Denis").withLastName("Zakharov").
                    withMobilePhoneNumber("9023557076").withEmail("z.d.vlad96@mail.ru"));
        }
    }

    @Test
    public void testContactEdit() throws Exception {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Mark")
                .withLastName("Zakharov")
                .withMobilePhoneNumber("9023557076")
                .withEmail("z.d.vlad96@mail.ru");
        app.contact().edit(contactData);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));
    }


}
