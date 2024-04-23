package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.models.Transaction;
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

    public TransactionService() {
    }

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public Transaction saveTransaction(Transaction transaction){
        return transactionDAO.save(transaction);
    }

    public Optional<Transaction> getTransactionById(int id){
        return  Optional.of(transactionDAO.getById(id));
    }

    //admins only
    public List<Transaction> getAllTransactions(){
        return transactionDAO.findAll();
    }
//    @Query("from transactions where ")
//    public List<Transaction>getAllTransactionsForUser(){
//
//    }
}
