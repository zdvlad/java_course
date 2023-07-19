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
    public void ensurePredications() {
        if(app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Denis")
                    .withLastName("Markov")
                    .withMobilePhoneNumber("5553535")
                    .withEmail("test@mail.ru")
                    .withAddress("ул. Летняя 9")
                    .withEmail2("test1@mail.ru")
                    .withEmail3("test2@mail.ru")
                    .withHomePhoneNumber("9999")
                    .withWorkPhoneNumber("1100"));
        }
    }

    @Test
    public void testContactEdit() throws Exception {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contactData = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("Denis")
                .withLastName("Markov")
                .withMobilePhoneNumber("5553535")
                .withEmail("test@mail.ru")
                .withAddress("ул. Летняя 9")
                .withEmail2("test1@mail.ru")
                .withEmail3("test2@mail.ru")
                .withHomePhoneNumber("9999")
                .withWorkPhoneNumber("1100");
        app.contact().edit(contactData, app.getProperties());
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));

        verifyContactListinUI();
    }
}
