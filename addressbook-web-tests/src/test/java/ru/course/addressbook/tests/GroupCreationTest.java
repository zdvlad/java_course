package ru.course.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().Group();
        List<GroupData> before =  app.group().list();
        GroupData group = new GroupData().withName("test1");
        app.group().create(group);
        List<GroupData> after =  app.group().list();
        Assert.assertEquals(after.size(), before.size()+1);

        group.withId(after.stream().max((x1, x2)->Integer.compare(x1.getId(), x2.getId())).get().getId());
        before.add(group);
        Comparator<? super GroupData> byId = (x1, x2)->Integer.compare(x1.getId(),x2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
