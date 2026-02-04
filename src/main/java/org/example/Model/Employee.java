package org.example.Model;

public class Employee {
    private String firstName;
    private String lastName;
    private String login;
    private Role role;
    private String token;

    public Employee(String firstName, String lastName, String login, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.role = role;
        this.token = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }
}
