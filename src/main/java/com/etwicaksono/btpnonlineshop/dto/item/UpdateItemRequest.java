package com.etwicaksono.btpnonlineshop.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class UpdateItemRequest {
   @NotNull(message = "{item.validation.itemsID.required}")
   private Integer itemsID;

   @NotNull(message = "{item.validation.name.required}")
   private String name;

   @NotNull(message = "{item.validation.code.required}")
   private String code;

   @NotNull(message = "{item.validation.stock.required}")
   private Integer stock;

   @NotNull(message = "{item.validation.price.required}")
   private Integer price;

   @NotNull(message = "{item.validation.isAvailable.required}")
   private Integer isAvailable;

   private LocalDate lastReStock;
}
