package com.etwicaksono.btpnonlineshop.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.customer.CreateCustomerRequest;
import com.etwicaksono.btpnonlineshop.dto.customer.GetListCustomerRequest;
import com.etwicaksono.btpnonlineshop.dto.customer.UpdateCustomerRequest;
import com.etwicaksono.btpnonlineshop.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
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

   @DeleteMapping(value = "/delete/{customerID}")
   public ResponseEntity<WebResponse<Object>> deleteCustomer(@PathVariable("customerID") Integer customerID) {
      return customerService.deleteCustomer(customerID);
   }

   @GetMapping(value = "/list")
   public ResponseEntity<WebResponse<Object>> listCustomer(
         @RequestParam("pageNumber") String pageNumber,
         @RequestParam("pageSize") String pageSize,
         @RequestParam("sortDirection") String sortDirection,
         @RequestParam(value = "customerName", required = false) String customerName,
         @RequestParam(value = "customerAddress", required = false) String customerAddress,
         @RequestParam(value = "customerCode", required = false) String customerCode,
         @RequestParam(value = "customerPhone", required = false) String customerPhone,
         @RequestParam(value = "isActive", required = false) String isActive) {

      return customerService.getCustomer(GetListCustomerRequest.builder()
            .pageNumber(pageNumber)
            .pageSize(pageSize)
            .sortDirection(sortDirection)
            .customerName(customerName)
            .customerAddress(customerAddress)
            .customerCode(customerCode)
            .customerPhone(customerPhone)
            .isActive(isActive)
            .build());
   }

}
