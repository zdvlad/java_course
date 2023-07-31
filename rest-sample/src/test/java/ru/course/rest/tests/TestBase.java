package ru.course.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.course.rest.appmanager.ApplicationManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        return app.rest().getStatusIssue(issueId);
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
