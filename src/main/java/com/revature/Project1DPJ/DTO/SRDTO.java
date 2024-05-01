package com.revature.Project1DPJ.DTO;

public class SRDTO {

    private int transactionId;
    private int checkingAccountNumber;
    private int savingsAccountNumber;

    private UserDTO user;

    public SRDTO() {
    }

    public SRDTO(int checkingAccountNumber, UserDTO user) {
        this.checkingAccountNumber = checkingAccountNumber;
        this.user = user;
    }

    public SRDTO(int transactionId, int checkingAccountNumber, UserDTO user) {
        this.transactionId = transactionId;
        this.checkingAccountNumber = checkingAccountNumber;
        this.user = user;
    }

    public SRDTO(int transactionId, int checkingAccountNumber, int savingsAccountNumber, UserDTO user) {
        this.transactionId = transactionId;
        this.checkingAccountNumber = checkingAccountNumber;
        this.savingsAccountNumber = savingsAccountNumber;
        this.user = user;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCheckingAccountNumber() {
        return checkingAccountNumber;
    }

    public void setCheckingAccountNumber(int checkingAccountNumber) {
        this.checkingAccountNumber = checkingAccountNumber;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
