package com.etwicaksono.btpnonlineshop.dto.customer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListCustomerRequest {
   @NotNull(message = "{pageNumber.required}")
   private String pageNumber;

   @NotNull(message = "{pageSize.required}")
   private String pageSize;

   @NotNull(message = "{sortDirection.required}")
   private String sortDirection;
}
