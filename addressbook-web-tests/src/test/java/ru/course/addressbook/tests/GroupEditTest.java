package ru.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupEditTest extends TestBase {

    @BeforeMethod
    public void ensurePredications() {
        app.goTo().Group();
        if (!app.group().isThereGroup()) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupEdit() throws Exception {

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData groupData = new GroupData().
                withId(before.get(index).getId()).
                withName("test1").
                withHeader("test2").
                withFooter("test3");
        app.group().editingGroup(index, groupData);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(groupData);
        Comparator<? super GroupData> byId = (x1, x2) -> Integer.compare(x1.getId(), x2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
