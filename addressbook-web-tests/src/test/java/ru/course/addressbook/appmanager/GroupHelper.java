package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.course.addressbook.model.GroupData;
import ru.course.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {
    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupParams(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    private void selectGroupById(int id) {
        wd.findElement(By.xpath("//input[@value = '" + id + "']")).click();
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void initEditGroup() {
        click(By.name("edit"));
    }

    public void submitEditGroup() {
        click(By.name("update"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void create(GroupData groupData) {
        initGroupCreation();
        fillGroupParams(groupData);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void edit(GroupData group) {
        selectGroupById(group.getId());
        initEditGroup();
        fillGroupParams(group);
        submitEditGroup();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroup();
        returnToGroupPage();
    }


    public boolean isThereGroup() {
        return isElementExist(By.name("selected[]"));
    }

    public List<GroupData> list() {
        List<GroupData> group = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            group.add(new GroupData().withId(id).withName(name));
        }
        return group;
    }

    public Groups all() {
        Groups group = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            group.add(new GroupData().withId(id).withName(name));
        }
        return group;
    }

}
