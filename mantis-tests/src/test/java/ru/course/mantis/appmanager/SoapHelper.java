package ru.course.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.course.mantis.model.Issue;
import ru.course.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mcp = getMantisConnect();
        ProjectData[] projects = mcp.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"));
        return Arrays.asList(projects).stream()
                .map(p -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("web.mantisConnect")));
    }

    public Issue createIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mcp = getMantisConnect();
        String[] categories = mcp.mc_project_get_categories(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDesc());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mcp.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"), issueData);
        IssueData createdIssueData = mcp.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"), issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withDesc(createdIssueData.getDescription())
                .withSummary(createdIssueData.getSummary())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));
    }

    public boolean getStatusIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mcp = getMantisConnect();
        IssueData issueData = mcp.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"), BigInteger.valueOf(issueId));
        return issueData.getResolution().getName().equals("open");
    }
}
