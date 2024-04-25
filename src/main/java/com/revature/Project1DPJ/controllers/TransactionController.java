package com.revature.Project1DPJ.controllers;

import com.revature.Project1DPJ.models.Transaction;
import com.revature.Project1DPJ.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    public TransactionController() {
    }

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction){
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        if(savedTransaction != null){
            return new ResponseEntity<>(savedTransaction,OK);
        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") int id){
        Optional<Transaction> foundTransaction = transactionService.getTransactionById(id);
        if(foundTransaction.isPresent()){
            return new ResponseEntity<>(foundTransaction.get(), OK);
        }
        return new ResponseEntity<>(NOT_FOUND);

    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(),OK);
    }

//    @GetMapping("user/{id}")
//    public ResponseEntity<List<Transaction>> getAllTransactionsByUserID(@PathVariable("id") int id){
//      Optional<List<Transactions>> usersTransactions = transactionService.getAllTransactionsByUserId(id);
//      if(foundTransaction.isPresent()){
//          return new ResponseEntity<>(usersTranasactions.get(), OK);
//        }
//       return new ResponseEntity<>(NOT_FOUND);
//    }

}
