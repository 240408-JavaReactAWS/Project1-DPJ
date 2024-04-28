package com.revature.Project1DPJ.util;

import com.revature.Project1DPJ.exceptions.AccountException;
import com.revature.Project1DPJ.exceptions.InsufficientFundsException;
import com.revature.Project1DPJ.models.Account;
import com.revature.Project1DPJ.models.AccountStatus;

public class AccountUtil {

    public static boolean isAccountEnabled(Account acct){
        if(acct.getAccountStatus().equals(AccountStatus.DISABLED)){
            throw new AccountException("Your account is disabeld");
        }else{
            return true;
        }
    }

    public static boolean sufficientFunds(Account acct, double amount){
        if(amount > acct.getBalance()){
            throw new InsufficientFundsException("Not enough funds to complete transaction");
        }
        else{
            return true;
        }
    }
}
