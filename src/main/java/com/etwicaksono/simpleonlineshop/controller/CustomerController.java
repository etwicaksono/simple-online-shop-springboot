package com.etwicaksono.simpleonlineshop.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etwicaksono.simpleonlineshop.dto.WebResponse;
import com.etwicaksono.simpleonlineshop.dto.customer.CreateCustomerRequest;
import com.etwicaksono.simpleonlineshop.dto.customer.GetListCustomerRequest;
import com.etwicaksono.simpleonlineshop.dto.customer.UpdateCustomerRequest;
import com.etwicaksono.simpleonlineshop.service.CustomerService;
import com.etwicaksono.simpleonlineshop.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Tag(name = "Customer", description = "API for Customer")
@RequestMapping("/customer")
public class CustomerController {
   @Autowired
   private CustomerService customerService;

   @Autowired
   private HttpServletResponse response;

   @Autowired
   private ReportService reportService;

   /**
    * Create a new customer.
    *
    * @param name     the name of the customer
    * @param address  the address of the customer
    * @param code     the code of the customer
    * @param phone    the phone number of the customer
    * @param isActive the active status of the customer
    * @param userPic  the picture of the customer (optional)
    * @return the response entity containing the result of the operation
    */
   @Operation(summary = "Create Customer", description = "Create a new customer")
   @ApiResponses({
         @ApiResponse(responseCode = "200", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "400", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "500", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         })
   })
   @PostMapping(value = "/create", consumes = { "multipart/form-data" })
   public ResponseEntity<WebResponse<Object>> createCustomer(
         @Parameter(name = "name", description = "the name of the customer", required = true) @RequestParam("name") String name,
         @Parameter(name = "address", description = "the address of the customer", required = true) @RequestParam("address") String address,
         @Parameter(name = "code", description = "the code of the customer", required = true) @RequestParam("code") String code,
         @Parameter(name = "phone", description = "the phone number of the customer", required = true) @RequestParam("phone") String phone,
         @Parameter(name = "isActive", description = "the active status of the customer", required = true) @RequestParam("isActive") Boolean isActive,
         @Parameter(name = "pic", description = "the picture of the customer (optional)", required = false) @RequestParam(value = "pic", required = false) MultipartFile userPic) {
      CreateCustomerRequest request = CreateCustomerRequest.builder()
            .name(name)
            .address(address)
            .code(code)
            .phone(phone)
            .isActive(isActive)
            .pic(userPic)
            .build();
      return customerService.createCustomer(request);
   }

   /**
    * Update an existing customer.
    *
    * @param customerID the ID of the customer to be updated
    * @param name       the name of the customer
    * @param address    the address of the customer
    * @param code       the code of the customer
    * @param phone      the phone number of the customer
    * @param isActive   the active status of the customer
    * @param userPic    the picture of the customer (optional)
    * @return the response entity containing the result of the operation
    */
   @Operation(summary = "Update Customer", description = "Update an existing customer")
   @ApiResponses({
         @ApiResponse(responseCode = "200", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "400", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "500", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         })
   })
   @PutMapping(value = "/update/{customerID}", consumes = { "multipart/form-data" })
   public ResponseEntity<WebResponse<Object>> updateCustomer(
         @Parameter(name = "customerID", description = "the ID of the customer to be updated", required = true) @PathVariable("customerID") Integer customerID,
         @Parameter(name = "name", description = "the name of the customer", required = true) @RequestParam("name") String name,
         @Parameter(name = "address", description = "the address of the customer", required = true) @RequestParam("address") String address,
         @Parameter(name = "code", description = "the code of the customer", required = true) @RequestParam("code") String code,
         @Parameter(name = "phone", description = "the phone number of the customer", required = true) @RequestParam("phone") String phone,
         @Parameter(name = "isActive", description = "the active status of the customer", required = true) @RequestParam("isActive") Boolean isActive,
         @Parameter(name = "pic", description = "the picture of the customer (optional)", required = false) @RequestParam(value = "pic", required = false) MultipartFile userPic) {

      UpdateCustomerRequest request = UpdateCustomerRequest.builder()
            .customerID(customerID)
            .name(name)
            .address(address)
            .code(code)
            .phone(phone)
            .isActive(isActive)
            .pic(userPic)
            .build();
      return customerService.updateCustomer(request);
   }

   /**
    * Get detail of a customer.
    *
    * @param customerID the ID of the customer to be retrieved
    * @return the response entity containing the result of the operation, which is
    *         a WebResponse object
    */
   @Operation(summary = "Detail Customer", description = "Get detail of a customer")
   @ApiResponses({
         @ApiResponse(responseCode = "200", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "400", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "500", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         })
   })
   @GetMapping(value = "/detail/{customerID}")
   public ResponseEntity<WebResponse<Object>> findCustomer(
         @Parameter(name = "customerID", description = "the ID of the customer to be retrieved", required = true) @PathVariable("customerID") Integer customerID) {
      return customerService.findCustomer(customerID);
   }

   /**
    * Delete an existing customer.
    *
    * @param customerID the ID of the customer to be deleted
    * @return the response entity containing the result of the operation, which is
    *         a WebResponse object
    */
   @Operation(summary = "Delete Customer", description = "Delete an existing customer")
   @ApiResponses({
         @ApiResponse(responseCode = "200", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "400", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "500", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         })
   })
   @DeleteMapping(value = "/delete/{customerID}")
   public ResponseEntity<WebResponse<Object>> deleteCustomer(
         @Parameter(name = "customerID", description = "the ID of the customer to be deleted", required = true) @PathVariable("customerID") Integer customerID) {
      return customerService.deleteCustomer(customerID);
   }

   /**
    * List Customers - Get list of customers
    *
    * @param pageNumber      PageNumber of customers list (required)
    * @param pageSize        PageSize of customers list (required)
    * @param sortDirection   Sort direction of customers list (required)
    * @param customerName    Customer name filter of customers list (optional)
    * @param customerAddress Customer address filter of customers list (optional)
    * @param customerCode    Customer code filter of customers list (optional)
    * @param customerPhone   Customer phone filter of customers list (optional)
    * @param isActive        Is active status filter of customers list (optional)
    * @return ResponseEntity containing WebResponse of Object
    */
   @Operation(summary = "List Customers", description = "Get list of customers")
   @ApiResponses({
         @ApiResponse(responseCode = "200", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "400", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         }),
         @ApiResponse(responseCode = "500", content = {
               @Content(mediaType = "application/json", schema = @Schema(implementation = WebResponse.class))
         })
   })
   @GetMapping(value = "/list")
   public ResponseEntity<WebResponse<Object>> listCustomer(
         @Parameter(name = "pageNumber", description = "PageNumber of customers list (required)", required = true) @RequestParam("pageNumber") Integer pageNumber,
         @Parameter(name = "pageSize", description = "PageSize of customers list (required)", required = true) @RequestParam("pageSize") Integer pageSize,
         @Parameter(name = "sortDirection", description = "Sort direction of customers list (required)", required = true) @RequestParam("sortDirection") String sortDirection,
         @Parameter(name = "customerName", description = "Customer name filter of customers list (optional)", required = false) @RequestParam(value = "customerName", required = false) String customerName,
         @Parameter(name = "customerAddress", description = "Customer address filter of customers list (optional)", required = false) @RequestParam(value = "customerAddress", required = false) String customerAddress,
         @Parameter(name = "customerCode", description = "Customer code filter of customers list (optional)", required = false) @RequestParam(value = "customerCode", required = false) String customerCode,
         @Parameter(name = "customerPhone", description = "Customer phone filter of customers list (optional)", required = false) @RequestParam(value = "customerPhone", required = false) String customerPhone,
         @Parameter(name = "isActive", description = "Is active status filter of customers list (optional)", required = false) @RequestParam(value = "isActive", required = false) Boolean isActive) {

      GetListCustomerRequest request = GetListCustomerRequest.builder()
            .pageNumber(pageNumber)
            .pageSize(pageSize)
            .sortDirection(sortDirection)
            .customerName(customerName)
            .customerAddress(customerAddress)
            .customerCode(customerCode)
            .customerPhone(customerPhone)
            .isActive(isActive)
            .build();

      return customerService.getCustomer(request);
   }

   @Operation(summary = "Customer Report", description = "Print list of customers as pdf file")
   @GetMapping(value = "/report")
   public void customerReport(
         @Parameter(name = "name", description = "Name of customer (optional)", required = false) @RequestParam(value = "name", required = false) String name,
         @Parameter(name = "address", description = "Address of customer (optional)", required = false) @RequestParam(value = "address", required = false) String address,
         @Parameter(name = "code", description = "Code of customer (optional)", required = false) @RequestParam(value = "code", required = false) String code,
         @Parameter(name = "phone", description = "Phone of customer (optional)", required = false) @RequestParam(value = "phone", required = false) String phone,
         @Parameter(name = "isActive", description = "Is active status filter of customers list (optional)", required = false) @RequestParam(value = "isActive", required = false) Boolean isActive

   ) throws IOException, SQLException, JRException {
      InputStream customerListReport = new ClassPathResource("report/Customer_List.jasper").getInputStream();
      Map<String, Object> parameters = new HashMap<>();
      if (name != null) {
         parameters.put("name", name.toUpperCase());
      }
      if (address != null) {
         parameters.put("address", address.toUpperCase());
      }
      if (code != null) {
         parameters.put("code", code.toUpperCase());
      }
      if (phone != null) {
         parameters.put("phone", phone.toUpperCase());
      }
      if (isActive != null) {
         parameters.put("is_active", isActive);
      }
      JasperPrint jasperPrint = reportService.generateJasperPrint(customerListReport, parameters);

      response.setContentType(MediaType.APPLICATION_PDF.toString());
      response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
            String.format("attachment; filename=customer-%s.pdf", Instant.now().toEpochMilli()));
      JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
   }

}
