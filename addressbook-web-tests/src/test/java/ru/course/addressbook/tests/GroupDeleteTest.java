package ru.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

public class GroupDeleteTest extends TestBase {

    @Test
    public void testGroupDelete() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if(! app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData("test", "test1", "test2"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
