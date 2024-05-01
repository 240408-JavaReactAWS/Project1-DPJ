package com.revature.Project1DPJ.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="transactions")
public class Transaction {

    //    @GeneratedValue( generator ="uuid2" )
//    @GenericGenerator(name="uuid2" ,strategy="org.hibernate.id.UUIDGenerator")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
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
    @Column(nullable = true)
    private int sr;
    @Column(nullable = true)
    private int sra;


    public Transaction() {
    }

    public Transaction(Account account, double total, TransactionType transactionType) {
        this.account = account;
        this.total = total;
        this.transactionType = transactionType;
    }

    public Transaction(int id, Account account, double total, TransactionType transactionType) {
        this.id = id;
        this.account = account;
        this.total = total;
        this.transactionType = transactionType;
    }

    public Transaction(int id, Account account, double total, java.sql.Date date, Time time, TransactionType transactionType) {
        this.id = id;
        this.account = account;
        this.total = total;
        this.date = date;
        this.time = time;
        this.transactionType = transactionType;

    }

    public Transaction(int id, Account account, double total, Date date, Time time, TransactionType transactionType, int sr, int sra) {
        this.id = id;
        this.account = account;
        this.total = total;
        this.date = date;
        this.time = time;
        this.transactionType = transactionType;
        this.sr = sr;
        this.sra=sra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public int getSra() {
        return sra;
    }

    public void setSra(int sra) {
        this.sra = sra;
    }
}
