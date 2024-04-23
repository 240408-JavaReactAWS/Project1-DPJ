package com.revature.Project1DPJ.repos;

import com.revature.Project1DPJ.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction,Integer> {
}
