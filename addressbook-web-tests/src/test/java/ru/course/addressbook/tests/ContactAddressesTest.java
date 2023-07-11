package ru.course.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

public class ContactAddressesTest extends TestBase{

    @Test
    public void testAllAddresses() throws Exception {
        app.contact().returnToMainPage();
        ContactData contactData = app.contact().all().iterator().next();

        ContactData contactDataFromEditForm = app.contact().fromEditForm(contactData);

        MatcherAssert.assertThat(contactData.getAddress(), equalTo(mergeAllAddresses(contactDataFromEditForm)));
    }

    private String mergeAllAddresses(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactAddressesTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String address) {
        return address.replaceAll("[-()]","");
    }
}
