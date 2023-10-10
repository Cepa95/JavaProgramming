package com.oss;

public class Color {
    private String firstName;

    private String lastName;

    public Color() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName(){
        return firstName.toUpperCase() + " " + lastName.toUpperCase();

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
