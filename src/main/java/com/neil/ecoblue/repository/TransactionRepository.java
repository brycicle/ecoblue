package com.neil.ecoblue.repository;

import com.neil.ecoblue.model.Item;
import com.neil.ecoblue.model.Redeem;
import com.neil.ecoblue.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByAccountAccountId(int itemId);
}
