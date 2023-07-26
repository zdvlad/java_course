package ru.course.mantis.tests;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.course.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.annotation.RegEx;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String username = String.format("user%s", now);
        String password = "password";
        app.registration().start(username, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationToLink = findConfirmationToLink(mailMessages, email);
        app.registration().finish(confirmationToLink, password);
        assertTrue(app.newSession().login(username, password));
    }

    private String findConfirmationToLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
