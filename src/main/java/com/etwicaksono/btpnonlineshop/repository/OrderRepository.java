package com.etwicaksono.btpnonlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.etwicaksono.btpnonlineshop.entity.OrderEntity;
import java.util.List;
import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>, JpaSpecificationExecutor<OrderEntity> {
   List<OrderEntity> getByCustomer(CustomerEntity customer);
}
