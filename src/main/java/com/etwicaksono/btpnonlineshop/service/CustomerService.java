package com.etwicaksono.btpnonlineshop.service;

import org.springframework.http.ResponseEntity;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.customer.CreateCustomerRequest;
import com.etwicaksono.btpnonlineshop.dto.customer.GetListCustomerRequest;
import com.etwicaksono.btpnonlineshop.dto.customer.UpdateCustomerRequest;

public interface CustomerService {
   ResponseEntity<WebResponse<Object>> createCustomer(CreateCustomerRequest request);

   ResponseEntity<WebResponse<Object>> updateCustomer(UpdateCustomerRequest request);

   ResponseEntity<WebResponse<Object>> findCustomer(Integer customerID);

   ResponseEntity<WebResponse<Object>> deleteCustomer(Integer customerID);

   ResponseEntity<WebResponse<Object>> getCustomer(GetListCustomerRequest request);
}