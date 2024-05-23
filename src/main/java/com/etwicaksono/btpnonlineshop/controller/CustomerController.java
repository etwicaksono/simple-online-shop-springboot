package com.etwicaksono.btpnonlineshop.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.customer.CreateCustomerRequest;
import com.etwicaksono.btpnonlineshop.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/customer")
public class CustomerController {
   @Autowired
   private CustomerService customerService;

   @PostMapping(value = "/create", consumes = { "multipart/form-data" })
   public ResponseEntity<WebResponse<Object>> createCustomer(
         @RequestParam("name") String name,
         @RequestParam("address") String address,
         @RequestParam("code") String code,
         @RequestParam("phone") String phone,
         @RequestParam("isActive") Integer isActive,
         @RequestParam("pic") MultipartFile userPic) {

      CreateCustomerRequest body = CreateCustomerRequest.builder()
            .name(name)
            .address(address)
            .code(code)
            .phone(phone)
            .isActive(isActive)
            .pic(userPic)
            .build();
      return customerService.createCustomer(body);
   }

}
