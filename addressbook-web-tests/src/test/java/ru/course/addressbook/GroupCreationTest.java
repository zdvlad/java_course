package ru.course.addressbook;


import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        gotoGroupPage();
        initGroupCreation();
        fillGroupParams(new GroupData("test", "test1", "test2"));
        submitGroupCreation();
        returnToGroupPage();
    }
}
