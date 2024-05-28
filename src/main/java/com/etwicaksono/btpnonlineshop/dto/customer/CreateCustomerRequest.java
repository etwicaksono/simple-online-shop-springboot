package com.etwicaksono.btpnonlineshop.dto.customer;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCustomerRequest {
   @NotNull(message = "{customer.name.required}")
   private String name;

   @NotNull(message = "{customer.address.required}")
   private String address;

   @NotNull(message = "{customer.code.required}")
   private String code;

   @NotNull(message = "{customer.phone.required}")
   private String phone;

   @NotNull(message = "{customer.isActive.required}")
   private Integer isActive;

   private MultipartFile pic;
}
