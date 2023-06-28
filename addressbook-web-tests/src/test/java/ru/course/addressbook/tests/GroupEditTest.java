package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupEditTest extends TestBase{

    @Test
    public void testGroupEdit() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData( "test1", null, null));
        }
        List<GroupData> before =  app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initEditGroup();
        GroupData groupData = new GroupData(before.get(before.size() - 1).getId(), "test1", "test2", "test3");
        app.getGroupHelper().fillGroupParams(groupData);
        app.getGroupHelper().submitEditGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after =  app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() -1);
        before.add(groupData);
        Comparator<? super GroupData> byId = (x1, x2)->Integer.compare(x1.getId(),x2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
