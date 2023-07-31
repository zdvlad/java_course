package ru.course.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;
import ru.course.mantis.model.Issue;
import ru.course.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(1);
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project p : projects) {
            System.out.println(p.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(1);
        Set<Project> projects = app.soap().getProjects();
        Issue is = new Issue().withSummary("Test Summary").withDesc("Desc").withProject(projects.iterator().next());
        Issue createdIsuue = app.soap().createIssue(is);
        assertEquals(is.getSummary(), createdIsuue.getSummary());
    }
}
