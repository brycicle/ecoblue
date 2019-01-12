package com.neil.ecoblue.repository;

import com.neil.ecoblue.model.Item;
import com.neil.ecoblue.model.Redeem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedeemRepository extends JpaRepository<Redeem,Integer> {
    Redeem findByRedeemId(int itemId);
}
