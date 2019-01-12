package com.neil.ecoblue.repository;

import com.neil.ecoblue.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByAccountId(int accountId);

    Account findByEmailAndPassword(String email, String password);
}
