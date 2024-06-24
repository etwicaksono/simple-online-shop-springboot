package com.etwicaksono.simpleonlineshop.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateItemRequest {
   @NotBlank(message = "{item.name.required}")
   private String name;

   @NotBlank(message = "{item.code.required}")
   private String code;

   @NotNull(message = "{item.stock.required}")
   private Integer stock;

   @NotNull(message = "{item.price.required}")
   private Integer price;

   @NotNull(message = "{item.isAvailable.required}")
   private Boolean isAvailable;
}
