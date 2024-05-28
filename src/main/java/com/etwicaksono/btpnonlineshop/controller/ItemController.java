package com.etwicaksono.btpnonlineshop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.item.CreateItemRequest;
import com.etwicaksono.btpnonlineshop.dto.item.UpdateItemRequest;
import com.etwicaksono.btpnonlineshop.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Tag(name = "Item", description = "API for Item")
@RequestMapping("/item")
public class ItemController {
   @Autowired
   private ItemService itemService;

   /**
    * Create a new item.
    *
    * @param name        Name of the item
    * @param code        Code of the item
    * @param stock       Stock number of the item
    * @param price       Price of the item
    * @param isAvailable Is available status of the item
    * @return ResponseEntity containing the result of the operation
    */
   @Operation(summary = "Create Item", description = "Create a new item")
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
   public ResponseEntity<WebResponse<Object>> createItem(
         @Parameter(name = "name", description = "Name of item", required = true) @RequestParam("name") String name,
         @Parameter(name = "code", description = "Code of item", required = true) @RequestParam("code") String code,
         @Parameter(name = "stock", description = "Stock number of item", required = true) @RequestParam("stock") Integer stock,
         @Parameter(name = "price", description = "Price of item", required = true) @RequestParam("price") Integer price,
         @Parameter(name = "isAvailable", description = "Is available status of item", required = true) @RequestParam("isAvailable") Integer isAvailable) {
      CreateItemRequest request = CreateItemRequest.builder()
            .name(name)
            .code(code)
            .stock(stock)
            .price(price)
            .isAvailable(isAvailable)
            .build();
      return itemService.createItem(request);
   }

   /**
    * Update an existing item.
    *
    * @param itemID      the ID of the item to be updated
    * @param name        the new name of the item
    * @param code        the new code of the item
    * @param stock       the new stock of the item
    * @param price       the new price of the item
    * @param isAvailable the new availability status of the item
    * @return a ResponseEntity containing a WebResponse with the updated item
    */
   @Operation(summary = "Update Item", description = "Update an existing item")
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
   @PutMapping(value = "/update/{itemID}")
   public ResponseEntity<WebResponse<Object>> updateCustomer(
         @Parameter(name = "itemID", description = "Item ID of item", required = true) @PathVariable("itemID") Integer itemID,
         @Parameter(name = "name", description = "Name of item", required = true) @RequestParam("name") String name,
         @Parameter(name = "code", description = "Code of item", required = true) @RequestParam("code") String code,
         @Parameter(name = "stock", description = "Stock of item", required = true) @RequestParam("stock") Integer stock,
         @Parameter(name = "price", description = "Price of item", required = true) @RequestParam("price") Integer price,
         @Parameter(name = "isAvailable", description = "IsAvailable of item", required = true) @RequestParam("isAvailable") Integer isAvailable) {

      UpdateItemRequest request = UpdateItemRequest.builder()
            .itemsID(itemID)
            .name(name)
            .code(code)
            .stock(stock)
            .price(price)
            .isAvailable(isAvailable)
            .build();
      return itemService.updateItem(request);
   }
}
