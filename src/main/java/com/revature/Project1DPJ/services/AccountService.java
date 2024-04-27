package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.UserModel;
import com.revature.Project1DPJ.repos.AccountDAO;
import com.revature.Project1DPJ.repos.TransactionDAO;
import com.revature.Project1DPJ.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;


@Service
public class AccountService {

    private AccountDAO accountDAO;
    private UserRepository userDAO;
    private TransactionDAO transactionDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO, UserRepository userDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.userDAO = userDAO;
        this.transactionDAO = transactionDAO;
    }

    public Account saveAccount(Account account){
        int id = account.getAccountOwner().getId();
        UserModel user = userDAO.getById(id);
        if(user!=null){
//            user.setId(id);
            account.setAccountOwner(user);
            account.setAccountTransactions(transactionDAO.findAllTransactionsByAccount(account.getId()));
        }
        return accountDAO.save(account);
    }
    public List<Account> getAllAccounts() {
        return this.accountDAO.findAll();
    }


    public Account getUserAccountById(int id) {
        Optional<Account> optionalAccount = this.accountDAO.findById(id);
        return optionalAccount.orElse(null);
    }

    public Optional<Account> findAccountByAccountNumber(int accountNumber){
        Optional<Account> foundAccount= Optional.ofNullable(accountDAO.findAccountByAccountNumber(accountNumber));
        return foundAccount;
    }


    public boolean deleteUserAccountById(int id) {

        if (!this.accountDAO.existsById(id)) return true;

        this.accountDAO.deleteById(id);

        // if account object exists in the database, return false. Otherwise, return true
        return !this.accountDAO.existsById(id);
    }

}
