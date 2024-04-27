package com.revature.Project1DPJ.repos;
import com.revature.Project1DPJ.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
    public Account findAccountByAccountNumber(int accountNumber);
}
