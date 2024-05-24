package com.etwicaksono.btpnonlineshop.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;

import jakarta.transaction.Transactional;

public interface CustomerRepository
      extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {
   boolean existsByCustomerCodeAndCustomerIDNot(String customerCode, Integer customerID);

   boolean existsByCustomerPhoneAndCustomerIDNot(String customerPhone, Integer customerID);

   boolean existsByCustomerCode(String customerCode);

   boolean existsByCustomerPhone(String customerPhone);

   @Modifying
   @Transactional
   @Query(nativeQuery = true, value = "UPDATE customers SET customer_name = :customerName, customer_address = :customerAddress, customer_code = :customerCode, customer_phone = :customerPhone, is_active = :isActive, last_order_date = :lastOrderDate, pic = :pic WHERE customer_id = :customerID")
   void updateCustomer(
         @Param("customerName") String customerName,
         @Param("customerAddress") String customerAddress,
         @Param("customerCode") String customerCode,
         @Param("customerPhone") String customerPhone,
         @Param("isActive") Integer isActive,
         @Param("lastOrderDate") LocalDate lastOrderDate,
         @Param("pic") String pic,
         @Param("customerID") Integer customerID);
}
