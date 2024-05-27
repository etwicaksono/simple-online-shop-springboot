package com.etwicaksono.btpnonlineshop.dto.customer;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
   private Integer customerID;
   private String name;
   private String address;
   private String code;
   private String phone;
   private Integer isActive;
   private String pic;
   private LocalDate lastOrderDate;
}
