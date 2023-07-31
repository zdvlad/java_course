package ru.course.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import ru.course.rest.model.Issue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException, URISyntaxException {
        //skipIfNotFixed(582);
        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSubject("Issue worktest").withDesc("Description of worktest");
        int issueId = app.rest().createIssue(newIssue);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
