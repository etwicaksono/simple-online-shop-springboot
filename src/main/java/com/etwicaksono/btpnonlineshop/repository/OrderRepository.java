package com.etwicaksono.btpnonlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etwicaksono.btpnonlineshop.entity.OrderEntity;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>, JpaSpecificationExecutor<OrderEntity> {
   List<OrderEntity> getByCustomer(CustomerEntity customer);

   @Modifying
   @Transactional
   @Query(nativeQuery = true, value = "UPDATE orders SET order_code = :orderCode, order_date = :orderDate, total_price = :totalPrice, customer_id = :customerID, items_id = :itemsID, quantity = :quantity WHERE order_id = :orderID")
   void updateOrder(
         @Param("orderCode") String orderCode,
         @Param("orderDate") LocalDate orderDate,
         @Param("totalPrice") Integer totalPrice,
         @Param("customerID") Integer customerID,
         @Param("itemsID") Integer itemsID,
         @Param("quantity") Integer quantity,
         @Param("orderID") Integer orderID);
}
