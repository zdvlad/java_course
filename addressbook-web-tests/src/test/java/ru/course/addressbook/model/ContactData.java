package ru.course.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String secondName;
    private final String phoneNumber;
    private final String email;

    public ContactData(String firstName, String secondName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
