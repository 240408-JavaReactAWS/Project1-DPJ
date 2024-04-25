package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.Transaction;
import com.revature.Project1DPJ.repos.AccountDAO;
import com.revature.Project1DPJ.repos.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    //hello

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    AccountDAO accountDAO;

    public TransactionService() {
    }

    public TransactionService(TransactionDAO transactionDAO, AccountDAO accountDAO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }

    public Transaction saveTransaction(Transaction transaction){
        int id= transaction.getAccount().getId();
        Account account= accountDAO.getById(id);
        if(account!=null){
            transaction.setAccount(account);
            return transactionDAO.save(transaction);
        }
        return null;
    }

    public Optional<Transaction> getTransactionById(int id){
        return  Optional.of(transactionDAO.getById(id));
    }

    //admins only
    public List<Transaction> getAllTransactions(){
        return transactionDAO.findAll();
    }

    public List<Transaction> getAllTransactionsByAccountId(int id){
        return transactionDAO.findAllTransactionsByAccount(id);
    }
//    @Query("from transactions where ")
//    public List<Transaction>getAllTransactionsForUser(){
//
//    }
}
