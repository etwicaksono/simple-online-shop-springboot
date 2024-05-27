package com.etwicaksono.btpnonlineshop.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListItemRequest {
   @NotNull(message = "{pageNumber.required}")
   private String pageNumber;

   @NotNull(message = "{pageSize.required}")
   private String pageSize;

   @NotNull(message = "{sortDirection.required}")
   private String sortDirection;

   private String customerName;
   private String customerAddress;
   private String customerCode;
   private String customerPhone;
   private String isActive;
}
