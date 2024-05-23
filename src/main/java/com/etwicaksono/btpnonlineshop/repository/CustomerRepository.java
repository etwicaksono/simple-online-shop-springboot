package com.etwicaksono.btpnonlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;

public interface CustomerRepository
      extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {
}
