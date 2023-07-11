package com.ricky.jpa.jpabook.repository;

import com.ricky.jpa.jpabook.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @PersistenceContext
    EntityManager em = null;

    @Query(value = "select * from TB_ORDERS where ORDER_ID = :orderId", nativeQuery = true)
    Order findOne(@Param("orderId") Long orderId);


}
