package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupEditTest extends TestBase{

    @BeforeMethod
    public void ensurePredications()
    {
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData( "test1", null, null));
        }
    }

    @Test
    public void testGroupEdit() throws Exception {

        List<GroupData> before =  app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        GroupData groupData = new GroupData(before.get(index).getId(), "test1", "test2", "test3");
        app.getGroupHelper().editingGroup(index, groupData);
        List<GroupData> after =  app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(groupData);
        Comparator<? super GroupData> byId = (x1, x2)->Integer.compare(x1.getId(),x2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
