package ru.course.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePassswordHelper extends HelperBase {

    public ChangePassswordHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseURL") + "/login.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void reset(String whichUser) {
        login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"));
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));
        //wd.get("http://localhost/mantisbt-1.2.20/manage_user_page.php");
        click(By.linkText(whichUser));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void change(String confirmationToLink, String whichPassword) {
        wd.get(confirmationToLink);
        type(By.name("password"), app.getProperty("web.defaultPass"));
        type(By.name("password_confirm"), app.getProperty("web.defaultPass"));
        click(By.cssSelector("input[value='Update User']"));
    }
}
