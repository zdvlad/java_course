package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

import java.util.List;

public class GroupEditTest extends TestBase{

    @Test
    public void testGroupEdit() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData( "test", "test1", "test2"));
        }
        List<GroupData> before =  app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initEditGroup();
        app.getGroupHelper().fillGroupParams(new GroupData( "Телефон", "Контакты", "Симка1"));
        app.getGroupHelper().submitEditGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after =  app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }
}
