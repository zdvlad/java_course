package ru.course.addressbook.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "address_in_groups")
public class GroupsWithContactData implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @Type(type = "int")
    private int id;

    @Id
    @Column(name = "group_id", unique = true)
    @Type(type = "int")
    private int group_id;

    public int getId() {
        return id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public GroupsWithContactData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupsWithContactData withGroupId(int groupId) {
        this.group_id = groupId;
        return this;
    }

    @Override
    public String toString() {
        return "GroupsWithContactData{" +
                "id=" + id +
                ", group_id=" + group_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupsWithContactData that = (GroupsWithContactData) o;
        return id == that.id &&
                group_id == that.group_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group_id);
    }
}
