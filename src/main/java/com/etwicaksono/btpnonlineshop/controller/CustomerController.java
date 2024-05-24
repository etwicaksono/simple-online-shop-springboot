package com.etwicaksono.btpnonlineshop.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.customer.CreateCustomerRequest;
import com.etwicaksono.btpnonlineshop.dto.customer.UpdateCustomerRequest;
import com.etwicaksono.btpnonlineshop.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
         @RequestParam(value = "pic", required = false) MultipartFile userPic) {

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

   @PutMapping(value = "/update/{customerID}", consumes = { "multipart/form-data" })
   public ResponseEntity<WebResponse<Object>> updateCustomer(
         @PathVariable("customerID") Integer customerID,
         @RequestParam("name") String name,
         @RequestParam("address") String address,
         @RequestParam("code") String code,
         @RequestParam("phone") String phone,
         @RequestParam("isActive") Integer isActive,
         @RequestParam(value = "pic", required = false) MultipartFile userPic) {

      UpdateCustomerRequest body = UpdateCustomerRequest.builder()
            .customerID(customerID)
            .name(name)
            .address(address)
            .code(code)
            .phone(phone)
            .isActive(isActive)
            .pic(userPic)
            .build();
      return customerService.updateCustomer(body);
   }

   @GetMapping(value = "/detail/{customerID}")
   public ResponseEntity<WebResponse<Object>> findCustomer(@PathVariable("customerID") Integer customerID) {
      return customerService.findCustomer(customerID);
   }

}
