package ru.course.rest.model;

import java.util.Objects;

public class Issue {

    private int id;
    private String subject;
    private String desc;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public Issue withDesc(String desc) {
        this.desc = desc;
        return this;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                Objects.equals(subject, issue.subject) &&
                Objects.equals(desc, issue.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, desc);
    }
}
