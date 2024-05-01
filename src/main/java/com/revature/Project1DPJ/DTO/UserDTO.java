package com.revature.Project1DPJ.DTO;


import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.models.UserStatus;
import com.revature.Project1DPJ.models.UserType;
import jakarta.persistence.Column;

import java.util.List;


public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private UserStatus userStatus;
    private UserType userType;
    private List<Account> accounts;

    public UserDTO() {
    }


    public UserDTO(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDTO(int id, String firstName, String lastName, String email, UserStatus userStatus, UserType userType, List<Account> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userStatus = userStatus;
        this.userType = userType;
        this.accounts = accounts;
    }

    public String getFirstName() {
        return firstName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
