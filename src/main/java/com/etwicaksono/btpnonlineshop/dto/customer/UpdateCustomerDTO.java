package com.etwicaksono.btpnonlineshop.dto.customer;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCustomerDTO {
   private String customerName;
   private String customerAddress;
   private String customerCode;
   private String customerPhone;
   private Integer isActive;
   private LocalDate lastOrderDate;
   private String pic;
   private Integer customerID;
}