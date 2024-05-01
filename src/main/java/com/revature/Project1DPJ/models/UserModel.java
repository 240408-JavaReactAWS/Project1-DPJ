package com.revature.Project1DPJ.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.exceptions.AccountException;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "users")
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
    @Enumerated(value=EnumType.STRING)
    private UserType role;
   @Enumerated(value=EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "accountOwner", cascade = CascadeType.ALL)

    @JsonManagedReference
    private List<Account> accounts;

    public UserModel() {
    }

    public UserModel(int id) {
        this.id = id;
    }

    public UserModel(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role=UserType.USER;
        this.userStatus=UserStatus.UNLOCKED;
    }

    public UserModel(int id, String firstName, String lastName, String email, UserType role, UserStatus userStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.userStatus = userStatus;
    }

    public UserModel(int id, String firstName, String lastName, String email, String password, UserType role, UserStatus userStatus, List<Account> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
        this.accounts = accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getAccount(AccountType type){
        if(type.equals(AccountType.CHECKING)){
            for(Account acct: this.accounts){
                if(acct.getAccountType().equals(type)){
                    return acct;
                }
            }
        throw new AccountException("Checking account does not exist");
        }else if(type.equals(AccountType.SAVINGS)){
            for(Account acct: this.accounts){
                if(acct.getAccountType().equals(type)){
                    return acct;
                }
            }
            throw new AccountException("Savings account does not exist");
        }
        throw new AccountException("Accounts does not exist");
    }

    public Account getSavingsAccount(AccountType type){
        for(Account acct: this.accounts){
            if(acct.getAccountType().equals(type)){
                return acct;
            }
        }
        throw new AccountException("Savings account does not exist");
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}