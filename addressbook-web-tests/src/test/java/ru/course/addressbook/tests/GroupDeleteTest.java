package ru.course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;
import ru.course.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePredications()
    {
        app.goTo().groupPage();
        if(! app.group().isThereGroup())
        {
            app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test2"));
        }
    }

    @Test
    public void testGroupDelete() throws Exception {
        Groups before =  app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after =  app.db().groups();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedGroup)));

        verifyGroupListinUI();
    }
}
