package com.revature.Project1DPJ.controllers;

import com.revature.Project1DPJ.DTO.TransactionDTO;
import com.revature.Project1DPJ.models.Transaction;
import com.revature.Project1DPJ.models.TransactionType;
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
@CrossOrigin(origins = {"http://localhost:3000"})
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    public TransactionController() {
    }

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> saveTransaction(@RequestBody TransactionDTO transaction){
        TransactionDTO savedTransaction;
        if(transaction.getType().equals(TransactionType.DEPOSIT) || transaction.getType().equals(TransactionType.WITHDRAWAL)){
            savedTransaction = transactionService.saveTransactionWD(transaction);
        }else{
            savedTransaction = transactionService.saveTransactionSR(transaction);
        }
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

    @GetMapping("user/{id}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByUserID(@PathVariable("id") int id){
        List<Transaction> usersTransactions = (transactionService.getAllTransactionsByAccountId(id));
        if(usersTransactions != null){
          return new ResponseEntity<>(usersTransactions, OK);
        }
       return new ResponseEntity<>(NOT_FOUND);
    }

}
