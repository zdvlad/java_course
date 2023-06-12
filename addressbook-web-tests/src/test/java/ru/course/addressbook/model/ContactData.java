package ru.course.addressbook.model;

public class ContactData {
    private final String firtsName;
    private final String secondName;
    private final String phoneNumber;
    private final String email;

    public ContactData(String firtsName, String secondName, String phoneNumber, String email) {
        this.firtsName = firtsName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirtsName() {
        return firtsName;
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
