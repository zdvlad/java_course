package ru.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTest extends TestBase {

    @BeforeMethod
    public void checkGroupsExits() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Group").withHeader("Group header").withFooter("Group footer"));
            app.contact().returnToMainPage(app.getProperties());
        }

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
    public void testContactAddToGroup() {
        Contacts contacts = app.db().contacts();//берем из бд контакты
        ContactData contact = contacts.iterator().next();//выбираем какой-то один контакт

        Groups groups = app.db().groups();//берем из бд группы
        GroupData group = groups.iterator().next();//выбираем какую-то группу

        GroupsWithContactData groupsWithContactData = new GroupsWithContactData().withId(contact.getId()).withGroupId(group.getId());

        //если контакт в группе
        if(app.contact().hasGroup(contact, group.getId())){
            app.contact().outGroup(contact, group);
            app.contact().returnToMainPage(app.getProperties());
        }

        GroupWithContacts before = app.db().groupWithContact();//берем  из бд связи

        app.contact().inGroup(contact, group);//добавляем контакт в группу

        GroupWithContacts after = app.db().groupWithContact();//берем снова из бд связи

        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(groupsWithContactData)));

        System.out.println(before.withAdded(groupsWithContactData));
        System.out.println(after);
    }
}
