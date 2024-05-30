package com.etwicaksono.btpnonlineshop.dto.order;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListOrderRequest implements Serializable {
   @NotNull(message = "{pageNumber.required}")
   private String pageNumber;

   @NotNull(message = "{pageSize.required}")
   private String pageSize;

   @NotNull(message = "{sortDirection.required}")
   private String sortDirection;

   private String orderCode;
}
