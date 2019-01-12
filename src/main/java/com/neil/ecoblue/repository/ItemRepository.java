package com.neil.ecoblue.repository;

import com.neil.ecoblue.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    Item findByItemId(int itemId);

}
