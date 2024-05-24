package com.etwicaksono.btpnonlineshop.service;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.customer.CreateCustomerRequest;
import com.etwicaksono.btpnonlineshop.dto.customer.UpdateCustomerDTO;
import com.etwicaksono.btpnonlineshop.dto.customer.UpdateCustomerRequest;
import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;
import com.etwicaksono.btpnonlineshop.repository.CustomerRepository;
import com.etwicaksono.btpnonlineshop.utils.ResponseUtil;

import io.minio.ObjectWriteResponse;
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
         String userPic = null;
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

         if (!body.getPic().isEmpty()) {
            MultipartFile file = body.getPic();

            // Get the original file name
            String fileName = file.getOriginalFilename();

            // Upload file to MinIO server
            String uniqueId = String.valueOf(Instant.now().toEpochMilli());
            ObjectWriteResponse uploaded = minioService.upload(
                  bucketName,
                  String.format("customer/%s-%s", uniqueId, fileName),
                  file);

            userPic = uploaded.object();
         }

         CustomerEntity customer = CustomerEntity.builder()
               .customerName(body.getName())
               .customerAddress(body.getAddress())
               .customerCode(body.getCode())
               .customerPhone(body.getPhone())
               .isActive(body.getIsActive())
               .pic(userPic)
               .build();

         customerRepository.save(customer);

         String messageTemplate = messageSource.getMessage("customer.created.success", null, Locale.getDefault());
         String message = MessageFormat.format(messageTemplate, body.getCode());

         return ResponseUtil.success201Response(message);
      } catch (Exception e) {
         log.error(e.getMessage());
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> updateCustomer(UpdateCustomerRequest body) {
      validator.validate(body);
      try {
         String userPic = null;
         LocalDate lastOrderDate = null;
         Integer customerID = body.getCustomerID();
         Optional<CustomerEntity> existingCustomer = customerRepository.findById(customerID);
         if (!existingCustomer.isPresent()) {
            return ResponseUtil
                  .error400Response(
                        messageSource.getMessage("customer.validation.customerID.invalid", null, Locale.getDefault()));
         }
         userPic = existingCustomer.get().getPic();
         lastOrderDate = existingCustomer.get().getLastOrderDate();

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

         if (!body.getPic().isEmpty()) {
            // delete old file
            if (!userPic.isEmpty()) {
               minioService.delete(bucketName, userPic);
            }

            MultipartFile file = body.getPic();

            // Get the original file name
            String fileName = file.getOriginalFilename();

            // Upload file to MinIO server
            String uniqueId = String.valueOf(Instant.now().toEpochMilli());
            ObjectWriteResponse uploaded = minioService.upload(
                  bucketName,
                  String.format("customer/%s-%s", uniqueId, fileName),
                  file);

            userPic = uploaded.object();
         }

         UpdateCustomerDTO updateCustomerDTO = UpdateCustomerDTO.builder()
               .customerName(body.getName())
               .customerAddress(body.getAddress())
               .customerCode(body.getCode())
               .customerPhone(body.getPhone())
               .isActive(body.getIsActive())
               .lastOrderDate(lastOrderDate)
               .pic(userPic)
               .customerID(customerID)
               .build();
         customerRepository.updateCustomer(updateCustomerDTO);

         String messageTemplate = messageSource.getMessage("customer.updated.success", null, Locale.getDefault());
         String message = MessageFormat.format(messageTemplate, body.getCode());

         return ResponseUtil.success200Response(message, null);
      } catch (Exception e) {
         log.error(e.getMessage());
         return ResponseUtil.error500Response(e.getMessage());
      }
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
