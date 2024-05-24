package com.etwicaksono.btpnonlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etwicaksono.btpnonlineshop.dto.customer.UpdateCustomerDTO;
import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;

import jakarta.transaction.Transactional;

public interface CustomerRepository
      extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {
   boolean existsByCustomerCode(String customerCode);

   boolean existsByCustomerPhone(String customerPhone);

   @Modifying
   @Transactional
   @Query(nativeQuery = true, value = "UPDATE customers SET customer_name = :customerDTO.customerName, customer_address = :customerDTO.customerAddress, customer_code = :customerDTO.customerCode, customer_phone = :customerDTO.customerPhone, is_active = :customerDTO.isActive, last_order_date = :customerDTO.lastOrderDate, pic = :customerDTO.pic WHERE customer_id = :customerDTO.customerID")
   void updateCustomer(@Param("customerDTO") UpdateCustomerDTO customerDTO);
}
