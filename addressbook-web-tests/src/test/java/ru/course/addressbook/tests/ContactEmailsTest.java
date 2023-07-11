package ru.course.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactEmailsTest extends TestBase{
    @Test
    public void testAllEmails() throws Exception {
        app.contact().returnToMainPage();
        ContactData contactData = app.contact().all().iterator().next();

        ContactData contactDataFromEditForm = app.contact().fromEditForm(contactData);

        MatcherAssert.assertThat(contactData.getAllEmails(), equalTo(mergeAllEmails(contactDataFromEditForm)));
    }

    private String mergeAllEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailsTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String emails) {
        return emails.replaceAll("\\s", "").replaceAll("[-()]","");
    }
}
