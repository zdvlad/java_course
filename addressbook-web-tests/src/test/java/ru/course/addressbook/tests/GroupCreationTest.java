package ru.course.addressbook.tests;


import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupParams(new GroupData("test", "test1", "test2"));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().returnToGroupPage();
    }
}
