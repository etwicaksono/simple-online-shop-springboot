package com.etwicaksono.btpnonlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.etwicaksono.btpnonlineshop.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer>, JpaSpecificationExecutor<ItemEntity> {

}
