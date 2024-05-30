package com.etwicaksono.btpnonlineshop.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateItemRequest {
   @NotNull(message = "{item.name.required}")
   private String name;

   @NotNull(message = "{item.code.required}")
   private String code;

   @NotNull(message = "{item.stock.required}")
   private Integer stock;

   @NotNull(message = "{item.price.required}")
   private Integer price;

   @NotNull(message = "{item.isAvailable.required}")
   private Boolean isAvailable;
}
