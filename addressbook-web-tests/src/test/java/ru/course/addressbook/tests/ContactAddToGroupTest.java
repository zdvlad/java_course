package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
        Contacts before = app.db().contacts();//берем из бд контакты
        ContactData contactToAdd = before.iterator().next();//выбираем какой-то один контакт

        Groups groups = app.db().groups();//берем из бд группы
        GroupData group = groups.iterator().next();//выбираем какую-то группу

        //если контакт в группе
        existGroupWithContact(contactToAdd, group);

        app.contact().inGroup(contactToAdd, group);//добавляем контакт в группу

        Contacts after = app.db().contacts();

        ContactData contactAdded = after.stream().filter(
                c->c.getId() == contactToAdd.getId()).collect(Collectors.toSet()).iterator().next();//ищем добавленный контакт

        Assert.assertTrue(app.contact().hasGroup(contactAdded, group.getId()));
    }

    private void existGroupWithContact(ContactData contactAdd, GroupData group) {
        if(app.contact().hasGroup(contactAdd, group.getId())){
            app.contact().outGroup(contactAdd, group);//удалим контакт из группы
            app.contact().returnToMainPage(app.getProperties());
        }
    }
}
