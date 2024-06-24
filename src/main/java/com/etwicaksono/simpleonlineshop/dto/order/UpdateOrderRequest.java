package com.etwicaksono.simpleonlineshop.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateOrderRequest {
   @NotNull(message = "{orderID.required}")
   private Integer orderID;

   @NotNull(message = "{customerID.required}")
   private Integer customerID;

   @NotNull(message = "{order.itemsID.required}")
   private Integer itemsID;

   @NotNull(message = "{order.quantity.required}")
   private Integer quantity;
}
