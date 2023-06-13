package ru.course.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTest extends TestBase {

    @Test
    public void testGroupDelete() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getNavigationHelper().returnToGroupPage();
    }
}
