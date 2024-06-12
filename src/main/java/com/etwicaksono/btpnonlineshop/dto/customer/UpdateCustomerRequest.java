package com.etwicaksono.btpnonlineshop.dto.customer;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class UpdateCustomerRequest {
   @NotNull(message = "{customerID.required}")
   private Integer customerID;

   @NotBlank(message = "{customer.name.required}")
   private String name;

   @NotBlank(message = "{customer.address.required}")
   private String address;

   @NotBlank(message = "{customer.code.required}")
   private String code;

   @NotBlank(message = "{customer.phone.required}")
   private String phone;

   @NotNull(message = "{customer.isActive.required}")
   private Boolean isActive;

   private LocalDate lastOrderDate;

   private MultipartFile pic;
}
