package com.etwicaksono.btpnonlineshop.dto.item;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
   private Integer itemsID;
   private String itemsName;
   private String itemsCode;
   private Integer stock;
   private Integer price;
   private Integer isAvailable;
   private LocalDate lastReStock;
}
