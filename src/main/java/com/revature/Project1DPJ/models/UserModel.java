package com.revature.Project1DPJ.models;

import jakarta.persistence.*;

@Entity
@Table(name = "/users")
public class UserModel {

    @Id
    @GeneratedValue
    private int userId;
    @Column(nullable = false)
    private String firstName ;
    @Column(nullable = false)
    private String lastName ;
    @Column(nullable = false)
    private String email ;
    @Column(nullable = false)
    private String password ;


    public UserModel() {
    }

    public UserModel(String lastName, String email, String firstName, String password) {
        this.lastName = lastName;
        this.email = email;
        this.firstName = firstName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}