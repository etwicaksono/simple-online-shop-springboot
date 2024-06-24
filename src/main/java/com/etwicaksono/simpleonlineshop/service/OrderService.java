package com.etwicaksono.simpleonlineshop.service;

import org.springframework.http.ResponseEntity;

import com.etwicaksono.simpleonlineshop.dto.WebResponse;
import com.etwicaksono.simpleonlineshop.dto.order.CreateOrderRequest;
import com.etwicaksono.simpleonlineshop.dto.order.GetListOrderRequest;
import com.etwicaksono.simpleonlineshop.dto.order.UpdateOrderRequest;

public interface OrderService {
   ResponseEntity<WebResponse<Object>> createOrder(CreateOrderRequest request);

   ResponseEntity<WebResponse<Object>> updateOrder(UpdateOrderRequest request);

   ResponseEntity<WebResponse<Object>> findOrder(Integer orderID);

   ResponseEntity<WebResponse<Object>> deleteOrder(Integer orderID);

   ResponseEntity<WebResponse<Object>> getOrder(GetListOrderRequest request);

}
