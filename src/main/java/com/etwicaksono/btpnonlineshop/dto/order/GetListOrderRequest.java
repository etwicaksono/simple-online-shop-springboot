package com.etwicaksono.btpnonlineshop.dto.order;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListOrderRequest implements Serializable {
   @NotBlank(message = "{pageNumber.required}")
   private String pageNumber;

   @NotBlank(message = "{pageSize.required}")
   private String pageSize;

   @NotBlank(message = "{sortDirection.required}")
   private String sortDirection;

   private String orderCode;
}
