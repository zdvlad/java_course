package ru.course.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.course.addressbook.appmanager.ApplicationManager;
import ru.course.addressbook.model.ContactData;
import ru.course.addressbook.model.Contacts;
import ru.course.addressbook.model.GroupData;
import ru.course.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();
    }


    public void verifyGroupListinUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroup = app.db().groups();
            Groups uiGroup = app.group().all();
            assertThat(uiGroup, equalTo(dbGroup.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListinUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c.getId())
                            .withFirstName(c.getFirstName())
                            .withLastName(c.getLastName())
                            .withAddress(c.getAddress()))
                    .collect(Collectors.toSet())));
        }
    }
}
