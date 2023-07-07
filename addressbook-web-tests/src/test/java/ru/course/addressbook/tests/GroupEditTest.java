package ru.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.addressbook.model.GroupData;
import ru.course.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData groupData = new GroupData().
                withId(modifiedGroup.getId()).
                withName("test1").
                withHeader("test2").
                withFooter("test3");
        app.group().edit(modifiedGroup);
        Groups after = app.group().all();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(groupData)));
    }
}
