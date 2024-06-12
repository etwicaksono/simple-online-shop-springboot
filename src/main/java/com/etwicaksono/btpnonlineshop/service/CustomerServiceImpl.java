package com.etwicaksono.btpnonlineshop.service;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etwicaksono.btpnonlineshop.dto.Pagination;
import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.customer.CreateCustomerRequest;
import com.etwicaksono.btpnonlineshop.dto.customer.CustomerDto;
import com.etwicaksono.btpnonlineshop.dto.customer.GetListCustomerRequest;
import com.etwicaksono.btpnonlineshop.dto.customer.UpdateCustomerRequest;
import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;
import com.etwicaksono.btpnonlineshop.repository.CustomerRepository;
import com.etwicaksono.btpnonlineshop.service.specification.CustomerSpecification;
import com.etwicaksono.btpnonlineshop.utils.Constants;
import com.etwicaksono.btpnonlineshop.utils.ResponseUtil;

import io.minio.ObjectWriteResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
   @Autowired
   private CustomerRepository customerRepository;

   @Autowired
   private ValidationService validator;

   @Autowired
   private MessageSource messageSource;

   @Autowired
   private MinioService minioService;

   @Value("${minio.bucketName}")
   private String bucketName;

   @Override
   public ResponseEntity<WebResponse<Object>> createCustomer(CreateCustomerRequest request) {
      validator.validate(request);
      try {
         String userPic = null;
         if (customerRepository.existsByCustomerCode(request.getCode())) {
            return ResponseUtil
                  .errorValidationResponse(
                        String.format("code: %s",
                              Constants.getMessage(messageSource, Constants.CUSTOMER_CODE_IS_EXIST)));
         }

         if (customerRepository.existsByCustomerPhone(request.getPhone())) {
            return ResponseUtil
                  .errorValidationResponse(String.format("phone: %s",
                        Constants.getMessage(messageSource, Constants.CUSTOMER_PHONE_IS_EXIST)));
         }

         if (request.getPic() != null && !request.getPic().isEmpty()) {
            MultipartFile file = request.getPic();

            // Get the original file name
            String fileName = file.getOriginalFilename();

            // Upload file to MinIO server
            String uniqueId = String.valueOf(Instant.now().toEpochMilli());
            log.info("Uploading image");
            ObjectWriteResponse uploaded = minioService.upload(
                  bucketName,
                  String.format("customer/%s-%s", uniqueId, fileName),
                  file);

            userPic = uploaded.object();
         }

         CustomerEntity customer = CustomerEntity.builder()
               .customerName(request.getName())
               .customerAddress(request.getAddress())
               .customerCode(request.getCode())
               .customerPhone(request.getPhone())
               .isActive(request.getIsActive())
               .pic(userPic)
               .build();

         customerRepository.save(customer);

         CustomerDto result = CustomerDto
               .builder()
               .customerID(customer.getCustomerID())
               .name(request.getName())
               .address(request.getAddress())
               .code(request.getCode())
               .phone(request.getPhone())
               .isActive(request.getIsActive())
               .pic(minioService.generateMinioURL(bucketName, userPic))
               .lastOrderDate(customer.getLastOrderDate())
               .build();

         String message = MessageFormat.format(Constants.getMessage(messageSource, Constants.CUSTOMER_CREATED_SUCCESS),
               request.getCode());

         return ResponseUtil.success200Response(message, result);
      } catch (Exception e) {
         log.error(e.getMessage());
         e.printStackTrace();
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> updateCustomer(UpdateCustomerRequest request) {
      validator.validate(request);
      try {
         String userPic = null;
         LocalDate lastOrderDate = null;
         Integer customerID = request.getCustomerID();
         Optional<CustomerEntity> existingCustomer = customerRepository.findById(customerID);
         if (!existingCustomer.isPresent()) {
            return ResponseUtil.errorValidationResponse(
                  String.format("customerID: %s", Constants.getMessage(messageSource, Constants.CUSTOMER_ID_INVALID)));
         }
         userPic = existingCustomer.get().getPic();
         lastOrderDate = existingCustomer.get().getLastOrderDate();

         if (customerRepository.existsByCustomerCodeAndCustomerIDNot(request.getCode(), customerID)) {
            return ResponseUtil
                  .errorValidationResponse(String.format("code: %s",
                        Constants.getMessage(messageSource, Constants.CUSTOMER_CODE_IS_EXIST)));
         }

         if (customerRepository.existsByCustomerPhoneAndCustomerIDNot(request.getPhone(), customerID)) {
            return ResponseUtil
                  .errorValidationResponse(String.format("phone: %s",
                        Constants.getMessage(messageSource, Constants.CUSTOMER_PHONE_IS_EXIST)));
         }

         if (request.getLastOrderDate() != null) {
            lastOrderDate = request.getLastOrderDate();
         }

         if (request.getPic() != null && !request.getPic().isEmpty()) {
            // delete old file
            if (userPic != null && !userPic.isEmpty()) {
               minioService.delete(bucketName, userPic);
            }

            MultipartFile file = request.getPic();

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

         customerRepository.updateCustomer(
               request.getName(),
               request.getAddress(),
               request.getCode(),
               request.getPhone(),
               request.getIsActive(),
               lastOrderDate,
               userPic,
               customerID);

         CustomerDto result = CustomerDto
               .builder()
               .customerID(customerID)
               .name(request.getName())
               .address(request.getAddress())
               .code(request.getCode())
               .phone(request.getPhone())
               .isActive(request.getIsActive())
               .pic(minioService.generateMinioURL(bucketName, userPic))
               .lastOrderDate(lastOrderDate)
               .build();

         String message = MessageFormat.format(Constants.getMessage(messageSource, Constants.CUSTOMER_UPDATED_SUCCESS),
               request.getCode());

         return ResponseUtil.success200Response(message, result);
      } catch (Exception e) {
         log.error(e.getMessage());
         e.printStackTrace();
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> findCustomer(Integer customerID) {
      try {
         Optional<CustomerEntity> existingCustomer = customerRepository.findById(customerID);
         if (!existingCustomer.isPresent()) {
            return ResponseUtil.errorValidationResponse(
                  String.format("customerID: %s", Constants.getMessage(messageSource, Constants.CUSTOMER_ID_INVALID)));
         }

         CustomerDto result = CustomerDto
               .builder()
               .customerID(customerID)
               .name(existingCustomer.get().getCustomerName())
               .address(existingCustomer.get().getCustomerAddress())
               .code(existingCustomer.get().getCustomerCode())
               .phone(existingCustomer.get().getCustomerPhone())
               .isActive(existingCustomer.get().getIsActive())
               .pic(minioService.generateMinioURL(bucketName, existingCustomer.get().getPic()))
               .lastOrderDate(existingCustomer.get().getLastOrderDate())
               .build();

         String message = MessageFormat.format(
               Constants.getMessage(messageSource, Constants.CUSTOMER_RETRIEVED_SUCCESS),
               existingCustomer.get().getCustomerCode());

         return ResponseUtil.success200Response(message, result);
      } catch (Exception e) {
         log.error(e.getMessage());
         e.printStackTrace();
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> deleteCustomer(Integer customerID) {
      try {
         Optional<CustomerEntity> existingCustomer = customerRepository.findById(customerID);
         if (!existingCustomer.isPresent()) {
            return ResponseUtil.errorValidationResponse(
                  String.format("customerID: %s", Constants.getMessage(messageSource, Constants.CUSTOMER_ID_INVALID)));
         }

         // delete file if exist
         if (existingCustomer.get().getPic() != null && !existingCustomer.get().getPic().isEmpty()) {
            minioService.delete(bucketName, existingCustomer.get().getPic());
         }

         customerRepository.deleteById(customerID);

         String message = MessageFormat.format(Constants.getMessage(messageSource, Constants.CUSTOMER_DELETED_SUCCESS),
               existingCustomer.get().getCustomerCode());

         return ResponseUtil.success200Response(message, null);
      } catch (Exception e) {
         log.error(e.getMessage());
         e.printStackTrace();
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> getCustomer(GetListCustomerRequest request) {
      validator.validate(request);
      try {
         int pageNumber = request.getPageNumber();
         int pageSize = request.getPageSize();
         List<CustomerDto> customers = new ArrayList<>();
         Sort.Direction sortDirection = request.getSortDirection()
               .equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
         CustomerSpecification customerSpecification = new CustomerSpecification(request);
         Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, "customerID"));
         Page<CustomerEntity> customerPage = customerRepository.findAll(customerSpecification, pageable);
         for (CustomerEntity customer : customerPage) {
            customers.add(CustomerDto
                  .builder()
                  .customerID(customer.getCustomerID())
                  .name(customer.getCustomerName())
                  .address(customer.getCustomerAddress())
                  .code(customer.getCustomerCode())
                  .phone(customer.getCustomerPhone())
                  .isActive(customer.getIsActive())
                  .pic(minioService.generateMinioURL(bucketName, customer.getPic()))
                  .lastOrderDate(customer.getLastOrderDate())
                  .build());
         }

         Pagination<List<CustomerDto>> result = Pagination
               .<List<CustomerDto>>builder()
               .data(customers)
               .totalPage(customerPage.getTotalPages())
               .totalItems(customerPage.getTotalElements())
               .build();

         return ResponseUtil.success200Response(
               Constants.getMessage(messageSource, Constants.CUSTOMERS_RETRIEVED_SUCCESS),
               result);
      } catch (Exception e) {
         log.error(e.getMessage());
         e.printStackTrace();
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

}
