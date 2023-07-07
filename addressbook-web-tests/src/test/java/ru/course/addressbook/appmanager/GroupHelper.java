package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.course.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public void editingGroup(int index, GroupData groupData) {
        selectGroup(index);
        initEditGroup();
        fillGroupParams(groupData);
        submitEditGroup();
        returnToGroupPage();
    }

    public void delete(int index) {
        selectGroup(index);
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
}
