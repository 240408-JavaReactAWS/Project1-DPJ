package com.revature.Project1DPJ.repos;

import com.revature.Project1DPJ.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction,Integer> {
    @Query(value="select *  FROM transactions  where account_account_id=?1",nativeQuery = true)
    List<Transaction>findAllTransactionsByAccount(int id);
}
