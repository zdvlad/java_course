package ru.course.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactPhonesTest extends TestBase {

    @Test
    public void testAllPhones() throws Exception {
        app.contact().returnToMainPage();
        ContactData contactData = app.contact().all().iterator().next();

        ContactData contactDataFromEditForm = app.contact().fromEditForm(contactData);

        MatcherAssert.assertThat(contactData.getAllPhones(), equalTo(mergeAllPhones(contactDataFromEditForm)));
    }

    private String mergeAllPhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phones) {
        return phones.replaceAll("\\s", "").replaceAll("[-()]","");
    }

}
