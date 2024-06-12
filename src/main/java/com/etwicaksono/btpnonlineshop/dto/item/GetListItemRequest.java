package com.etwicaksono.btpnonlineshop.dto.item;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListItemRequest {
   @NotBlank(message = "{pageNumber.required}")
   private String pageNumber;

   @NotBlank(message = "{pageSize.required}")
   private String pageSize;

   @NotBlank(message = "{sortDirection.required}")
   private String sortDirection;

   private String itemName;
   private String itemCode;
   private Boolean isAvailable;
}
