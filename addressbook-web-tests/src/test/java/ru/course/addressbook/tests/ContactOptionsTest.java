package ru.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactOptionsTest extends TestBase {

    @Test
    public void testAllOptions() throws Exception {
        app.contact().returnToMainPage(app.getProperties());
        ContactData contactData = app.contact().all().iterator().next();

        ContactData contactDataFromEditForm = app.contact().fromEditForm(contactData);

        assertThat(contactData.getAllPhones(), equalTo(mergeAllPhones(contactDataFromEditForm)));
        assertThat(contactData.getAddress(), equalTo(contactDataFromEditForm.getAddress().trim()));
        assertThat(contactData.getAllEmails(), equalTo(mergeAllEmails(contactDataFromEditForm)));
    }

    private String mergeAllPhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone().trim(), contact.getMobilePhone().trim(), contact.getWorkPhone().trim())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactOptionsTest::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }

    private String mergeAllEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail().trim(), contact.getEmail2().trim(), contact.getEmail3().trim())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }


    public static String cleanedPhones(String phones) {
        return phones.replaceAll("\\s", "").replaceAll("[-()]","");
    }

}
