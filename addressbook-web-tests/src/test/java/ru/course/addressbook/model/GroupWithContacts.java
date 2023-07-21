package ru.course.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GroupWithContacts extends ForwardingSet<GroupsWithContactData> {

    private Set<GroupsWithContactData> delegate;

    public GroupWithContacts(GroupWithContacts groups) {
        this.delegate = new HashSet<GroupsWithContactData>(groups.delegate);
    }

    public GroupWithContacts() {
        this.delegate = new HashSet<>();
    }

    public GroupWithContacts(Collection<GroupsWithContactData> groups) {
        this.delegate = new HashSet<GroupsWithContactData>(groups);
    }

    @Override
    protected Set<GroupsWithContactData> delegate() {
        return this.delegate;
    }

    public GroupWithContacts withAdded(GroupsWithContactData group) {
        GroupWithContacts groups = new GroupWithContacts(this);
        groups.add(group);
        return groups;
    }

    public GroupWithContacts without(GroupsWithContactData group) {
        GroupWithContacts groups = new GroupWithContacts(this);
        groups.remove(group);
        return groups;
    }
}
