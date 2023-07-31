package ru.course.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;
import ru.course.rest.model.Issue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class RestHelper {

    private ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Issue> getIssues() throws IOException, URISyntaxException {
        String json = getAllIssuesRequest();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    public int createIssue(Issue issue) throws IOException {
        String json = postRequest(issue);
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }


    private String getAllIssuesRequest() throws IOException, URISyntaxException {
        String s = getExecutor().execute(Request.Get(app.getProperty("rest.baseURL")+"?limit=300")).returnContent().asString();
        return s;
    }

    private String getIssueRequest(int issueId) throws IOException {
        String s = getExecutor().execute(Request.Get(String.format(app.getProperty("rest.issueIdURL"), issueId)))
                .returnContent().asString();
        return s;
    }

    private String postRequest(Issue newIssue) throws IOException {
        String s = getExecutor().execute(Request.Post(app.getProperty("rest.baseURL"))
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDesc())))
                .returnContent().asString();
        return s;
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth(app.getProperty("rest.auth"), app.getProperty("rest.password"));
    }

    public boolean getStatusIssue(int issueId) throws IOException {
        String json = getIssueRequest(issueId);
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issue = parsed.getAsJsonObject().get("issues").getAsJsonArray().iterator().next();
        return issue.getAsJsonObject().get("state_name").getAsString().equals("Open");
    }
}
