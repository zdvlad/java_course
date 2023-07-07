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
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData( "test1", "test1", "test2"));
        }
    }

    @Test
    public void testGroupDelete() throws Exception {
        List<GroupData> before =  app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        app.getGroupHelper().deletingGroup(index);
        List<GroupData> after =  app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
