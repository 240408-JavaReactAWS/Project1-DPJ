package com.revature.Project1DPJ.services;

import com.revature.Project1DPJ.DTO.SRDTO;
import com.revature.Project1DPJ.DTO.TransactionDTO;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.exceptions.TransactionException;
import com.revature.Project1DPJ.models.*;
import com.revature.Project1DPJ.repos.AccountDAO;
import com.revature.Project1DPJ.repos.TransactionDAO;
import com.revature.Project1DPJ.repos.UserRepository;
import com.revature.Project1DPJ.util.AccountUtil;
import com.revature.Project1DPJ.util.TransactionUtil;
import com.revature.Project1DPJ.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
        String sendersEmail= transactionDTO.getSender().getUser().getEmail();
        String receiversEmail= transactionDTO.getSender().getUser().getEmail();
        UserModel sender=userRepository.findUserByEmail(sendersEmail);
        UserModel receiver=userRepository.findUserByEmail(receiversEmail);
        if(sender!=null && receiver !=null && !transactionDTO.getType().equals(TransactionType.WITHDRAWAL)&& !transactionDTO.getType().equals(TransactionType.DEPOSIT)) {
            Account sendersAccount= accountDAO.findAccountByAccountNumber(transactionDTO.getSender().getCheckingAccountNumber());
            Account receiversAccount= accountDAO.findAccountByAccountNumber(transactionDTO.getReceiver().getCheckingAccountNumber());
            boolean sufficient = AccountUtil.sufficientFunds(sendersAccount, transactionDTO.getTotal());
            if(sufficient==true && sendersAccount!=null && receiversAccount!=null && sendersAccount.getAccountStatus()!= AccountStatus.DISABLED && receiversAccount.getAccountStatus()!= AccountStatus.DISABLED){
                if(transactionDTO.getDate()==null)
                    transactionDTO.setDate(Date.valueOf(LocalDate.now()));
                if(transactionDTO.getTime()==null)
                    transactionDTO.setTime(Time.valueOf(LocalTime.now()));
                sendersAccount.setBalance(sendersAccount.getBalance() - transactionDTO.getTotal());
                SRDTO srDTO = UserUtil.mapUserToSRDTO(transactionDTO.getId(),sendersAccount.getAccountNumber(),sender);
                SRDTO rsDTO=UserUtil.mapUserToSRDTO(transactionDTO.getId(),receiversAccount.getAccountNumber(),receiver);
                transactionDTO.setSender(srDTO);
                System.out.println(srDTO);
                System.out.println(rsDTO);
                transactionDTO.setReceiver(rsDTO);
                Transaction savedTransactionSender = TransactionUtil.mapTransactionDTOToTransactionSR(sendersAccount, receiversAccount.getAccountOwner().getId(), receiversAccount.getAccountNumber(), transactionDTO);
                Transaction savedSendersT=transactionDAO.save(savedTransactionSender);

                receiversAccount.setBalance((receiversAccount.getBalance()+transactionDTO.getTotal()));
                transactionDTO.setType(TransactionType.TRANSFER_RECEIVER);
                Transaction savedTransactionReceiver = TransactionUtil.mapTransactionDTOToTransactionSR(receiversAccount, sendersAccount.getAccountOwner().getId(), sendersAccount.getAccountNumber(), transactionDTO);
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
        String sendersEmail= transactionDTO.getSender().getUser().getEmail();
        UserModel sender=userRepository.findUserByEmail(sendersEmail);
        if(sender!=null){
            Account sendersAccount= accountDAO.findAccountByAccountNumber(transactionDTO.getSender().getCheckingAccountNumber());
            boolean sufficient = AccountUtil.sufficientFunds(sendersAccount, transactionDTO.getTotal());
            if(transactionDTO.getDate()==null)
                transactionDTO.setDate(Date.valueOf(LocalDate.now()));
            if(transactionDTO.getTime()==null)
                transactionDTO.setTime(Time.valueOf(LocalTime.now()));
            if(sufficient==true && sendersAccount!=null && transactionDTO.getType().equals(TransactionType.WITHDRAWAL)){
                sendersAccount.setBalance(sendersAccount.getBalance() - transactionDTO.getTotal());
            }else if(sendersAccount!=null && transactionDTO.getType().equals(TransactionType.DEPOSIT)){
                sendersAccount.setBalance(sendersAccount.getBalance() + transactionDTO.getTotal());
            }else{
                throw new TransactionException("Unable to complete transaction account(s) may not be active or have insufficient funds");
                }
            Transaction mappedToTran = TransactionUtil.mapTransactionDTOToTransactionWD(sendersAccount, transactionDTO);
            Transaction savedSendersT=transactionDAO.save(mappedToTran);
            if(savedSendersT !=null) return transactionDTO;
            }
        throw new TransactionException("Unable to find user(s)");
        }



    public Optional<Transaction> getTransactionById(int id){
        Optional<Transaction> transaction=transactionDAO.findById(id);
        if(transaction.isEmpty()){
            return null;
        }
        return transaction;


    }

    //admins only
    public List<TransactionDTO> getAllTransactions(){
        List<TransactionDTO>transactionDTOs=new ArrayList<>();
        transactionDAO.findAll().forEach(transaction -> {
            if(transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)||transaction.getTransactionType().equals(TransactionType.DEPOSIT)){
                transactionDTOs.add(TransactionUtil.mapTransactionWDToTransactionDTO(transaction));
            }else{
                UserDTO userDTO= UserUtil.mapUserToUserDTO(userRepository.findUserById(transaction.getSr()));
                transactionDTOs.add(TransactionUtil.mapTransactionSRToTransactionDTO(userDTO,transaction));
            }

        });
        return transactionDTOs;
    }

    public List<TransactionDTO> getAllTransactionsByAccountId(int id){
        List<TransactionDTO>transactionDTOs=new ArrayList<>();
        transactionDAO.findAllTransactionsByAccount(id).forEach(transaction -> {
            if(transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)||transaction.getTransactionType().equals(TransactionType.DEPOSIT)){
                transactionDTOs.add(TransactionUtil.mapTransactionWDToTransactionDTO(transaction));
            }else{
                UserDTO userDTO= UserUtil.mapUserToUserDTO(userRepository.findUserById(transaction.getSr()));
                transactionDTOs.add(TransactionUtil.mapTransactionSRToTransactionDTO(userDTO,transaction));
            }

        });
        return transactionDTOs;
    }



}
