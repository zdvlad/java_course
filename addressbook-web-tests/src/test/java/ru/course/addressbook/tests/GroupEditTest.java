package ru.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

public class GroupEditTest extends TestBase{

    @Test
    public void testGroupEdit() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initEditGroup();
        app.getGroupHelper().fillGroupParams(new GroupData("Телефон", "Контакты", "Симка1"));
        app.getGroupHelper().submitEditGroup();
        app.getNavigationHelper().returnToGroupPage();
    }
}
