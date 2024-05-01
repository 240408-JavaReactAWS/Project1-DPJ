package com.revature.Project1DPJ.util;

import com.revature.Project1DPJ.DTO.SRDTO;
import com.revature.Project1DPJ.DTO.UserDTO;
import com.revature.Project1DPJ.models.UserModel;

public class UserUtil {



    public static UserDTO mapUserToUserDTO(UserModel user){
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),user.getUserStatus(),user.getRole(),user.getAccounts());
    }
    public static SRDTO mapUserToSRDTO(int transactionId, int accountNumber, UserModel user){

        return new SRDTO(transactionId,accountNumber,UserUtil.mapUserToUserDTO(user));
    }
    public static SRDTO mapUserToSRDTO(int transactionId, int accountNumber, UserDTO user){

        return new SRDTO(transactionId,accountNumber,user);
    }
    public static SRDTO mapUserDTOToSRDTO(UserDTO user, int accountNumber){
        return new SRDTO(accountNumber,user);
    }

    public static SRDTO mapUserDTOToSRDTO(UserDTO user, int checkingAccountNumber, int savingsAccountNumber){
        return new SRDTO(checkingAccountNumber,savingsAccountNumber,user);
    }
}
