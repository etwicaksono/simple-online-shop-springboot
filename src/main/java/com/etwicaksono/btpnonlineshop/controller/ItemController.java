package com.etwicaksono.btpnonlineshop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.item.CreateItemRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Tag(name = "Item", description = "API for Item")
@RequestMapping("/item")
public class ItemController {
   @Autowired
   private ItemService itemService;

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
}
