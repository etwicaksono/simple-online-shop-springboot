package com.etwicaksono.btpnonlineshop.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class UpdateItemRequest {
   @NotBlank(message = "{itemsID.required}")
   private Integer itemsID;

   @NotBlank(message = "{item.name.required}")
   private String name;

   @NotBlank(message = "{item.code.required}")
   private String code;

   @NotBlank(message = "{item.stock.required}")
   private Integer stock;

   @NotBlank(message = "{item.price.required}")
   private Integer price;

   @NotNull(message = "{item.isAvailable.required}")
   private Boolean isAvailable;

   private LocalDate lastReStock;
}
