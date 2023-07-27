package ru.course.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.mantis.model.MailMessage;
import ru.course.mantis.model.UserData;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        List<UserData> users = app.db().users();
        String username = users.iterator().next().getUsername();
        String email = users.stream().filter(u->u.getUsername().equals(username)).collect(Collectors.toList()).iterator().next().getEmail();
        app.password().reset(username);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String resetPasswordLink = findResetPasswordToLink(mailMessages, email);
        app.password().change(resetPasswordLink, app.getProperty("web.defaultPass"));
        assertTrue(app.newSession().login(username, app.getProperty("web.defaultPass")));
    }

    private String findResetPasswordToLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
