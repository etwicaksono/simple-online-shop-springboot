package com.etwicaksono.btpnonlineshop.dto.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderRequest {
   @NotBlank(message = "{customerID.required}")
   private Integer customerID;

   @NotBlank(message = "{order.itemsID.required}")
   private Integer itemsID;

   @NotBlank(message = "{order.quantity.required}")
   private Integer quantity;
}
