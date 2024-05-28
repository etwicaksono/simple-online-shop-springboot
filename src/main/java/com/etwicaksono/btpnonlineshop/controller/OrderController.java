package com.etwicaksono.btpnonlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.order.CreateOrderRequest;
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
         @Parameter(name = "customerID", description = "ID of customer", required = true) @RequestParam("customerID") Integer customerID,
         @Parameter(name = "itemsID", description = "ID of items", required = true) @RequestParam("itemsID") Integer itemsID,
         @Parameter(name = "quantity", description = "Quantity of order", required = true) @RequestParam("quantity") Integer quantity) {
      CreateOrderRequest request = CreateOrderRequest.builder()
            .customerID(customerID)
            .itemsID(itemsID)
            .quantity(quantity)
            .build();
      return orderService.createOrder(request);
   }
}
