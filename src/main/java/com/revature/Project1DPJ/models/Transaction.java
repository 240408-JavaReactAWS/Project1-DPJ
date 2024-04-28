package com.revature.Project1DPJ.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.Project1DPJ.DTO.UserDTO;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
@Table(name="transactions")
public class Transaction {

    //    @GeneratedValue( generator ="uuid2" )
//    @GenericGenerator(name="uuid2" ,strategy="org.hibernate.id.UUIDGenerator")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    @ManyToOne()
    @JsonBackReference
    private Account account;
    @Column(nullable = false)
    private double total;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private java.sql.Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:MM")
    private Time time;
    @Enumerated(value=EnumType.STRING)
    private TransactionType transactionType;

    public Transaction() {
    }

    public Transaction(Account account, double total, TransactionType transactionType) {
        this.account = account;
        this.total = total;
        this.transactionType = transactionType;
    }

    public Transaction(int transactionId, Account account, double total, TransactionType transactionType) {
        this.transactionId = transactionId;
        this.account = account;
        this.total = total;
        this.transactionType = transactionType;
    }

    public Transaction(int transactionId, Account account, double total, java.sql.Date date, Time time, TransactionType transactionType) {
        this.transactionId = transactionId;
        this.account = account;
        this.total = total;
        this.date = date;
        this.time = time;
        this.transactionType = transactionType;

    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
