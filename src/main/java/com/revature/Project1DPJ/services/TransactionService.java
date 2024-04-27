package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.DTO.TransactionDTO;
import com.revature.Project1DPJ.exceptions.TransactionException;
import com.revature.Project1DPJ.models.*;
import com.revature.Project1DPJ.repos.AccountDAO;
import com.revature.Project1DPJ.repos.TransactionDAO;
import com.revature.Project1DPJ.repos.UserRepository;
import com.revature.Project1DPJ.util.AccountUtil;
import com.revature.Project1DPJ.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.SUPPORTS,
        readOnly = false,
        timeout = 30)
public class TransactionService {
    //hello

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    UserRepository userRepository;

    public TransactionService() {
    }

    public TransactionService(TransactionDAO transactionDAO, AccountDAO accountDAO, UserRepository userRepository) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
        this.userRepository = userRepository;
    }

    @Transactional
    public TransactionDTO saveTransactionSR(TransactionDTO transactionDTO){
        String sendersEmail= transactionDTO.getSender().getEmail();
        String receiversEmail= transactionDTO.getSender().getEmail();
        UserModel sender=userRepository.findUserByEmail(sendersEmail);
        UserModel receiver=userRepository.findUserByEmail(receiversEmail);
        if(sender!=null && receiver !=null && !transactionDTO.getType().equals(TransactionType.WITHDRAWAL)&& !transactionDTO.getType().equals(TransactionType.DEPOSIT)) {
            Account sendersAccount= accountDAO.findAccountByAccountNumber(transactionDTO.getSenderAccountNumber());
            Account receiversAccount= accountDAO.findAccountByAccountNumber(transactionDTO.getSenderAccountNumber());
            boolean sufficient = AccountUtil.sufficientFunds(sendersAccount, transactionDTO.getSentAmount());
            if(sufficient==true && sendersAccount!=null && receiversAccount!=null && sendersAccount.getAccountStatus()!= AccountStatus.DISABLED && receiversAccount.getAccountStatus()!= AccountStatus.DISABLED){
                if(transactionDTO.getDate()==null)
                    transactionDTO.setDate(Date.valueOf(LocalDate.now()));
                if(transactionDTO.getTime()==null)
                    transactionDTO.setTime(Time.valueOf(LocalTime.now()));
                sendersAccount.setBalance(sendersAccount.getBalance() - transactionDTO.getSentAmount());
                Transaction savedTransactionSender = TransactionUtil.mapTransactionDTOToTransaction(sendersAccount, transactionDTO);
                Transaction savedSendersT=transactionDAO.save(savedTransactionSender);

                receiversAccount.setBalance((receiversAccount.getBalance()+transactionDTO.getSentAmount()));
                transactionDTO.setType(TransactionType.TRANSFER_RECEIVER);
                Transaction savedTransactionReceiver = TransactionUtil.mapTransactionDTOToTransaction(receiversAccount, transactionDTO);
                Transaction savedReceiversT=transactionDAO.save(savedTransactionReceiver);
                if(savedSendersT != null && savedReceiversT !=null ){
                    return transactionDTO;
                }
//                account.addTransaction();
            }
            throw new TransactionException("Not able to find Active account(s)");
        }
        throw new TransactionException("Not able to find user(s)");

    }

    @Transactional
    public TransactionDTO saveTransactionWD(TransactionDTO transactionDTO){
        String sendersEmail= transactionDTO.getSender().getEmail();
        UserModel sender=userRepository.findUserByEmail(sendersEmail);
        if(sender!=null){
            Account sendersAccount= accountDAO.findAccountByAccountNumber(transactionDTO.getSenderAccountNumber());
            boolean sufficient = AccountUtil.sufficientFunds(sendersAccount, transactionDTO.getSentAmount());
            if(transactionDTO.getDate()==null)
                transactionDTO.setDate(Date.valueOf(LocalDate.now()));
            if(transactionDTO.getTime()==null)
                transactionDTO.setTime(Time.valueOf(LocalTime.now()));
            if(sufficient==true && sendersAccount!=null && transactionDTO.getType().equals(TransactionType.WITHDRAWAL)){
                sendersAccount.setBalance(sendersAccount.getBalance() - transactionDTO.getSentAmount());
            }else if(sendersAccount!=null && transactionDTO.getType().equals(TransactionType.DEPOSIT)){
                sendersAccount.setBalance(sendersAccount.getBalance() + transactionDTO.getSentAmount());
            }else{
                throw new TransactionException("Unable to complete transaction account(s) may not be active or have insufficient funds");
                }
            Transaction mappedToTran = TransactionUtil.mapTransactionDTOToTransaction(sendersAccount, transactionDTO);
            Transaction savedSendersT=transactionDAO.save(mappedToTran);
            if(savedSendersT !=null) return transactionDTO;
            }
        throw new TransactionException("Unable to find user(s)");
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



}
