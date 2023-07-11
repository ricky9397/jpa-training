package com.ricky.jpa.jpabook.repository;

import com.ricky.jpa.jpabook.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select * from ITEM where ITEM_ID = :itemId", nativeQuery = true)
    Item findOne(@Param("itemId") Long id);

}
