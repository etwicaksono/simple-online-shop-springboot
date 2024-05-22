package com.etwicaksono.btpnonlineshop.dto.customer;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCustomerRequest {
   @NotNull(message = "{customer.validation.name.required}")
   private String name;

   @NotNull(message = "{customer.validation.address.required}")
   private String address;

   @NotNull(message = "{customer.validation.code.required}")
   private String code;

   @NotNull(message = "{customer.validation.phone.required}")
   private String phone;

   @NotNull(message = "{customer.validation.isActive.required}")
   private Boolean isActive;

   private MultipartFile pic;
}
