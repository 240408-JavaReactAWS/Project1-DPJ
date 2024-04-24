package com.revature.Project1DPJ.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class UserModel {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstName ;
    @Column(nullable = false)
    private String lastName ;
    @Column(nullable = false)
    private String email ;
    @Column(nullable = false)
    private String password ;
    private String role;
    private boolean userStatus;

    @OneToMany
    private List<Account> accounts;



    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String email, boolean userStatus, String role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userStatus = userStatus;
        this.role = role;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUserId() {
        return id;
    }

    public void setUserId(int userId) {
        this.id = userId;
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
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }
}