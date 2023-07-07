package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

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
        Set<GroupData> before =  app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after =  app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }
}
