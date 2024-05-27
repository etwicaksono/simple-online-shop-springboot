package com.etwicaksono.btpnonlineshop.dto.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
   private Integer customerID;
   private String name;
   private String address;
   private String code;
   private String phone;
   private Integer isActive;
   private String pic;
}
