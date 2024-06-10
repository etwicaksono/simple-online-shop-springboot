package com.etwicaksono.btpnonlineshop.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.order.CreateOrderRequest;
import com.etwicaksono.btpnonlineshop.dto.order.GetListOrderRequest;
import com.etwicaksono.btpnonlineshop.dto.order.UpdateOrderRequest;
import com.etwicaksono.btpnonlineshop.service.OrderService;
import com.etwicaksono.btpnonlineshop.service.ReportService;

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

@RestController
@Tag(name = "Order", description = "Order API")
@RequestMapping("/order")
public class OrderController {
   @Autowired
   private OrderService orderService;

   @Autowired
   private HttpServletResponse response;

   @Autowired
   private ReportService reportService;

   /**
    * Create a new order.
    *
    * @param customerID ID of the customer
    * @param itemsID    ID of the items
    * @param quantity   Quantity of the order
    * @return ResponseEntity containing the WebResponse with the created order
    */
   @Operation(summary = "Create Order", description = "Create a new order")
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
   @PostMapping(value = "/create")
   public ResponseEntity<WebResponse<Object>> createOrder(
         @Parameter(name = "customerID", description = "ID of the customer", required = true) @RequestParam("customerID") Integer customerID,
         @Parameter(name = "itemsID", description = "ID of the items", required = true) @RequestParam("itemsID") Integer itemsID,
         @Parameter(name = "quantity", description = "Quantity of the order", required = true) @RequestParam("quantity") Integer quantity) {
      CreateOrderRequest request = CreateOrderRequest.builder()
            .customerID(customerID)
            .itemsID(itemsID)
            .quantity(quantity)
            .build();
      return orderService.createOrder(request);
   }

   /**
    * Update an existing order.
    *
    * @param orderID    the ID of the order to be updated
    * @param customerID the ID of the customer associated with the order
    * @param itemsID    the ID of the items associated with the order
    * @param quantity   the quantity of the order
    * @return a ResponseEntity containing the WebResponse with the updated order
    */
   @Operation(summary = "Update Order", description = "Update a new order")
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
   @PutMapping(value = "/update/{orderID}")
   public ResponseEntity<WebResponse<Object>> updateOrder(
         @Parameter(name = "orderID", description = "the ID of the order to be updated", required = true) @PathVariable("orderID") Integer orderID,
         @Parameter(name = "customerID", description = "the ID of the customer associated with the order", required = true) @RequestParam("customerID") Integer customerID,
         @Parameter(name = "itemsID", description = "the ID of the items associated with the order", required = true) @RequestParam("itemsID") Integer itemsID,
         @Parameter(name = "quantity", description = "the quantity of the order", required = true) @RequestParam("quantity") Integer quantity) {
      UpdateOrderRequest request = UpdateOrderRequest.builder()
            .orderID(orderID)
            .customerID(customerID)
            .itemsID(itemsID)
            .quantity(quantity)
            .build();
      return orderService.updateOrder(request);
   }

   /**
    * Get detail of an order.
    *
    * @param orderID the ID of the order to be retrieved
    * @return ResponseEntity containing the WebResponse with the order details
    */
   @Operation(summary = "Detail Order", description = "Get detail of an order")
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
   @GetMapping(value = "/detail/{orderID}")
   public ResponseEntity<WebResponse<Object>> findCustomer(
         @Parameter(name = "orderID", description = "the ID of the order to be retrieved", required = true) @PathVariable("orderID") Integer orderID) {
      return orderService.findOrder(orderID);
   }

   /**
    * Delete an existing order.
    *
    * @param orderID the ID of the order to be deleted
    * @return ResponseEntity containing the WebResponse with the result of the
    *         operation
    */
   @Operation(summary = "Delete Order", description = "Delete an existing order")
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
   @DeleteMapping(value = "/delete/{orderID}")
   public ResponseEntity<WebResponse<Object>> deleteCustomer(
         @Parameter(name = "orderID", description = "the ID of the order to be deleted", required = true) @PathVariable("orderID") Integer orderID) {
      return orderService.deleteOrder(orderID);
   }

   /**
    * Get list of orders.
    *
    * @param pageNumber    PageNumber of orders list
    * @param pageSize      PageSize of orders list
    * @param sortDirection Sort direction of orders list
    * @param orderCode     orderCode filter of orders list (optional)
    * @return ResponseEntity containing the WebResponse with the list of orders
    */
   @Operation(summary = "List Orders", description = "Get list of orders")
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
   public ResponseEntity<WebResponse<Object>> listItem(
         @Parameter(name = "pageNumber", description = "PageNumber of orders list", required = true) @RequestParam("pageNumber") String pageNumber,
         @Parameter(name = "pageSize", description = "PageSize of orders list", required = true) @RequestParam("pageSize") String pageSize,
         @Parameter(name = "sortDirection", description = "Sort direction of orders list", required = true) @RequestParam("sortDirection") String sortDirection,
         @Parameter(name = "orderCode", description = "orderCode filter of orders list (optional)", required = false) @RequestParam(value = "orderCode", required = false) String orderCode) {

      return orderService.getOrder(GetListOrderRequest.builder()
            .pageNumber(pageNumber)
            .pageSize(pageSize)
            .sortDirection(sortDirection)

            .build());
   }

   @Operation(summary = "Order Report", description = "Print list of orders as pdf file")
   @GetMapping(value = "/report")
   public void itemReport(
         @Parameter(name = "orderCode", description = "Code of order (optional)", required = false) @RequestParam(value = "orderCode", required = false) String orderCode,
         @Parameter(name = "customerCode", description = "Code of customer (optional)", required = false) @RequestParam(value = "customerCode", required = false) String customerCode,
         @Parameter(name = "itemCode", description = "Code of item (optional)", required = false) @RequestParam(value = "itemCode", required = false) String itemCode

   ) throws IOException, SQLException, JRException {
      InputStream itemListReport = new ClassPathResource("report/Order_List.jasper").getInputStream();
      Map<String, Object> parameters = new HashMap<>();
      if (orderCode != null) {
         parameters.put("order_code", orderCode.toUpperCase());
      }
      if (customerCode != null) {
         parameters.put("customer_code", customerCode.toUpperCase());
      }
      if (itemCode != null) {
         parameters.put("item_code", itemCode);
      }
      JasperPrint jasperPrint = reportService.generateJasperPrint(itemListReport, parameters);

      response.setContentType(MediaType.APPLICATION_PDF.toString());
      response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
            String.format("attachment; filename=order-%s.pdf", Instant.now().toEpochMilli()));
      JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
   }
}
