package com.etwicaksono.btpnonlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.order.CreateOrderRequest;
import com.etwicaksono.btpnonlineshop.dto.order.UpdateOrderRequest;
import com.etwicaksono.btpnonlineshop.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Order", description = "Order API")
@RequestMapping("/order")
public class OrderController {
   @Autowired
   private OrderService orderService;

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
}
