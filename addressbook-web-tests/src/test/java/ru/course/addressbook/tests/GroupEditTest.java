package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupEditTest extends TestBase {

    @BeforeMethod
    public void ensurePredications() {
        app.goTo().groupPage();
        if (!app.group().isThereGroup()) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupEdit() throws Exception {
        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData groupData = new GroupData().
                withId(modifiedGroup.getId()).
                withName("test1").
                withHeader("test2").
                withFooter("test3");
        app.group().edit(modifiedGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(groupData);
        Assert.assertEquals(before, after);
    }

}
