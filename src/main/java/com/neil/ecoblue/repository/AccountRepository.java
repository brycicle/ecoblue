package com.neil.ecoblue.repository;

import com.neil.ecoblue.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    //naka auto na dito yung mga save, delete, findAll kapag nag extend ka nyan na JpaRepository
    Account findByAccountId(int accountId);
    Account findByEmailAndPassword(String email, String password);
}
