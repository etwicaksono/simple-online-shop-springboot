package com.etwicaksono.btpnonlineshop.dto.order;

import java.time.LocalDate;

import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;
import com.etwicaksono.btpnonlineshop.entity.ItemEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {
   private Integer orderId;
   private String orderCode;
   private LocalDate orderDate;
   private Integer totalPrice;
   private Integer quantity;
   private CustomerEntity customer;
   private ItemEntity item;
}
