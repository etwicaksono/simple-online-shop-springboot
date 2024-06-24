package com.etwicaksono.simpleonlineshop.service;

import org.springframework.http.ResponseEntity;

import com.etwicaksono.simpleonlineshop.dto.WebResponse;
import com.etwicaksono.simpleonlineshop.dto.customer.CreateCustomerRequest;
import com.etwicaksono.simpleonlineshop.dto.customer.GetListCustomerRequest;
import com.etwicaksono.simpleonlineshop.dto.customer.UpdateCustomerRequest;

public interface CustomerService {
   ResponseEntity<WebResponse<Object>> createCustomer(CreateCustomerRequest request);

   ResponseEntity<WebResponse<Object>> updateCustomer(UpdateCustomerRequest request);

   ResponseEntity<WebResponse<Object>> findCustomer(Integer customerID);

   ResponseEntity<WebResponse<Object>> deleteCustomer(Integer customerID);

   ResponseEntity<WebResponse<Object>> getCustomer(GetListCustomerRequest request);
}