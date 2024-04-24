package com.revature.Project1DPJ.models;


import jakarta.persistence.*;

@Entity // Entity marks this class as one that needs to be created in the database
@Table(name = "accounts") // Table allows us to set any configuration details about the SQL table that we want
public class Account {

    @Id
    @Column (name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id_fk")
    @Column(name = "account_Owner_id")
    private UserModel accountOwnerId;

    @Column
    private double balance;


    public Account(String accountType, String accountNumber, UserModel accountOwnerId, double balance) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountOwnerId = accountOwnerId;
        this.balance = balance;
    }

    public Account(int id, String accountType, String accountNumber, UserModel accountOwnerId, double balance) {
        this.id = id;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountOwnerId = accountOwnerId;
        this.balance = balance;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public UserModel getAccountOwner() {
        return accountOwnerId;
    }

    public void setAccountOwnerId(UserModel accountOwnerId) {
        this.accountOwnerId = accountOwnerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
