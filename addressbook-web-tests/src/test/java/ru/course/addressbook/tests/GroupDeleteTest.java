package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePredications()
    {
        app.goTo().Group();
        if(! app.group().isThereGroup())
        {
            app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test2"));
        }
    }

    @Test
    public void testGroupDelete() throws Exception {
        List<GroupData> before =  app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after =  app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
