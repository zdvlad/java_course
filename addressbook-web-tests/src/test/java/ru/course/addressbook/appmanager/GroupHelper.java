package ru.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.course.addressbook.model.GroupData;

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

    public void selectGroup() {
        click(By.name("selected[]"));
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

    public void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupParams(new GroupData("test", "test1", "test2"));
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isThereGroup() {
        return isElementExist(By.name("selected[]"));
    }
}
