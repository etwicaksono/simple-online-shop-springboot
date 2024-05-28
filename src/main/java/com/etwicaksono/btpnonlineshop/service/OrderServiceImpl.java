package com.etwicaksono.btpnonlineshop.service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.order.CreateOrderRequest;
import com.etwicaksono.btpnonlineshop.dto.order.GetListOrderRequest;
import com.etwicaksono.btpnonlineshop.dto.order.OrderDto;
import com.etwicaksono.btpnonlineshop.dto.order.UpdateOrderRequest;
import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;
import com.etwicaksono.btpnonlineshop.entity.ItemEntity;
import com.etwicaksono.btpnonlineshop.entity.OrderEntity;
import com.etwicaksono.btpnonlineshop.repository.CustomerRepository;
import com.etwicaksono.btpnonlineshop.repository.ItemRepository;
import com.etwicaksono.btpnonlineshop.repository.OrderRepository;
import com.etwicaksono.btpnonlineshop.utils.Constants;
import com.etwicaksono.btpnonlineshop.utils.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
   private OrderRepository orderRepository;

   @Autowired
   private ItemRepository itemRepository;

   @Autowired
   private CustomerRepository customerRepository;

   @Autowired
   private MessageSource messageSource;

   @Override
   public ResponseEntity<WebResponse<Object>> createOrder(CreateOrderRequest request) {
      try {
         String orderCode = "";
         Integer totalPrice = 0;

         if (!customerRepository.existsById(request.getCustomerID())) {
            return ResponseUtil.error400Response(Constants.getMessage(messageSource, Constants.CUSTOMER_ID_INVALID));
         }

         if (!itemRepository.existsById(request.getItemsID())) {
            return ResponseUtil.error400Response(Constants.getMessage(messageSource, Constants.ITEMS_ID_INVALID));
         }
         if (request.getQuantity() <= 0) {
            return ResponseUtil
                  .error400Response(Constants.getMessage(messageSource, Constants.QUANTITY_INVALID));
         }

         CustomerEntity customer = customerRepository.findById(request.getCustomerID()).get();
         ItemEntity item = itemRepository.findById(request.getItemsID()).get();

         OrderEntity order = OrderEntity.builder()
               .orderCode(orderCode)
               .orderDate(LocalDate.now())
               .totalPrice(totalPrice)
               .quantity(request.getQuantity())
               .customer(customer)
               .item(item)
               .build();

         orderRepository.save(order);

         OrderDto result = OrderDto
               .builder()
               .orderId(order.getOrderId())
               .orderCode(order.getOrderCode())
               .orderDate(order.getOrderDate())
               .totalPrice(order.getTotalPrice())
               .quantity(order.getQuantity())
               .customer(customer)
               .item(item)
               .build();

         String message = MessageFormat.format(Constants.getMessage(messageSource, Constants.ORDER_CREATED_SUCCESS),
               order.getOrderCode());

         return ResponseUtil.success200Response(message, result);
      } catch (Exception e) {
         log.error(e.getMessage());
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> updateOrder(UpdateOrderRequest request) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
   }

   @Override
   public ResponseEntity<WebResponse<Object>> findOrder(Integer orderID) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'findOrder'");
   }

   @Override
   public ResponseEntity<WebResponse<Object>> deleteOrder(Integer orderID) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
   }

   @Override
   public ResponseEntity<WebResponse<Object>> getOrder(GetListOrderRequest request) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
   }

}
