package ru.course.addressbook.tests;


import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupParams(new GroupData("test", "test1", "test2"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }
}
