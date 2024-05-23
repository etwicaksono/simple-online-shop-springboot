package com.etwicaksono.btpnonlineshop.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.customer.CreateCustomerRequest;
import com.etwicaksono.btpnonlineshop.dto.customer.UpdateCustomerRequest;
import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;
import com.etwicaksono.btpnonlineshop.repository.CustomerRepository;
import com.etwicaksono.btpnonlineshop.utils.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
   @Autowired
   private CustomerRepository customerRepository;

   @Autowired
   private MessageSource messageSource;

   @Autowired
   private ValidationService validator;

   @Autowired
   private MinioService minioService;

   @Value("${minio.bucketName}")
   private String bucketName;

   @Override
   public ResponseEntity<WebResponse<Object>> createCustomer(CreateCustomerRequest body) {
      validator.validate(body);
      try {
         if (customerRepository.existsByCustomerCode(body.getCode())) {
            return ResponseUtil
                  .error400Response(
                        messageSource.getMessage("customer.validation.code.isExist", null, Locale.getDefault()));
         }

         if (customerRepository.existsByCustomerPhone(body.getPhone())) {
            return ResponseUtil
                  .error400Response(
                        messageSource.getMessage("customer.validation.phone.isExist", null, Locale.getDefault()));
         }

         CustomerEntity customer = CustomerEntity.builder()
               .customerName(body.getName())
               .customerAddress(body.getAddress())
               .customerCode(body.getCode())
               .customerPhone(body.getPhone())
               .isActive(body.getIsActive())
               // .pic(body.getPic())
               .build();

         customerRepository.save(customer);

         String messageTemplate = messageSource.getMessage("customer.created.success", null, Locale.getDefault());
         String message = String.format(messageTemplate, body.getCode());

         return ResponseUtil.success201Response(message);
      } catch (Exception e) {
         log.error(e.getMessage());
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> updateCustomer(UpdateCustomerRequest body) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
   }

   @Override
   public ResponseEntity<WebResponse<Object>> findCustomer(Integer customerID) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'findCustomer'");
   }

   @Override
   public ResponseEntity<WebResponse<Object>> deleteCustomer(Integer customerID) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
   }

}
