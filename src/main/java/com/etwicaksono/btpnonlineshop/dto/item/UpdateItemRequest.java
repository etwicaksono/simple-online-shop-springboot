package com.etwicaksono.btpnonlineshop.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class UpdateItemRequest {
   @NotNull(message = "{itemsID.required}")
   private Integer itemsID;

   @NotNull(message = "{item.name.required}")
   private String name;

   @NotNull(message = "{item.code.required}")
   private String code;

   @NotNull(message = "{item.stock.required}")
   private Integer stock;

   @NotNull(message = "{item.price.required}")
   private Integer price;

   @NotNull(message = "{item.isAvailable.required}")
   private Integer isAvailable;

   private LocalDate lastReStock;
}
