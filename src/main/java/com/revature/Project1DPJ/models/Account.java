package com.revature.Project1DPJ.models;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity // Entity marks this class as one that needs to be created in the database
@Table(name = "accounts") // Table allows us to set any configuration details about the SQL table that we want
public class Account {

    @Id
    @Column (name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "account_type", nullable = false)
    @Enumerated(value=EnumType.STRING)
    private AccountType accountType;

    @Column(name="account_number", unique = true)
    private int accountNumber;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name="account_owner")
    private UserModel accountOwner;

    @Column(name="account_balance")
    private double balance;

    @Column(name="account_status")
    @Enumerated(value=EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    private List<Transaction> accountTransactions;

    public Account() {
    }

    public Account(AccountType accountType, int accountNumber, UserModel accountOwner, double balance, AccountStatus accountStatus) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.accountStatus = accountStatus;
    }

    public Account(AccountType accountType, int accountNumber, UserModel accountOwner, double balance, AccountStatus accountStatus, List<Transaction> accountTransactions) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.accountStatus = accountStatus;
        this.accountTransactions = accountTransactions;
    }

    public Account(int id, AccountType accountType, int accountNumber, UserModel accountOwner, double balance, AccountStatus accountStatus, List<Transaction> accountTransactions) {
        this.id = id;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.accountStatus = accountStatus;
        this.accountTransactions = accountTransactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserModel getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(UserModel accountOwner) {
        this.accountOwner = accountOwner;
    }

    public List<Transaction> getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(List<Transaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
