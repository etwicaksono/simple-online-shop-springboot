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
   private Integer pageNumber;

   @NotNull(message = "{pageSize.required}")
   private Integer pageSize;

   @NotNull(message = "{sortDirection.required}")
   private String sortDirection;

   private String customerName;
   private String customerAddress;
   private String customerCode;
   private String customerPhone;
   private Boolean isActive;
}
