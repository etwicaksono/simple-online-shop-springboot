package com.etwicaksono.btpnonlineshop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.item.CreateItemRequest;
import com.etwicaksono.btpnonlineshop.dto.item.GetListItemRequest;
import com.etwicaksono.btpnonlineshop.dto.item.UpdateItemRequest;
import com.etwicaksono.btpnonlineshop.service.ItemService;
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

@RestController
@Tag(name = "Item", description = "API for Item")
@RequestMapping("/item")
public class ItemController {
   @Autowired
   private ItemService itemService;

   @Autowired
   private HttpServletResponse response;

   @Autowired
   private ReportService reportService;

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
         @Parameter(name = "name", description = "Name of the item", required = true) @RequestParam("name") String name,
         @Parameter(name = "code", description = "Code of the item", required = true) @RequestParam("code") String code,
         @Parameter(name = "stock", description = "Stock number of the item", required = true) @RequestParam("stock") Integer stock,
         @Parameter(name = "price", description = "Price of the item", required = true) @RequestParam("price") Integer price,
         @Parameter(name = "isAvailable", description = "Is available status of the item", required = true) @RequestParam("isAvailable") Boolean isAvailable) {
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
         @Parameter(name = "itemID", description = "the ID of the item to be updated", required = true) @PathVariable("itemID") Integer itemID,
         @Parameter(name = "name", description = "the new name of the item", required = true) @RequestParam("name") String name,
         @Parameter(name = "code", description = "the new code of the item", required = true) @RequestParam("code") String code,
         @Parameter(name = "stock", description = "the new stock of the item", required = true) @RequestParam("stock") Integer stock,
         @Parameter(name = "price", description = "the new price of the item", required = true) @RequestParam("price") Integer price,
         @Parameter(name = "isAvailable", description = "the new availability status of the item", required = true) @RequestParam("isAvailable") Boolean isAvailable) {

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

   /**
    * Get detail of a item.
    *
    * @param itemID the ID of the item to be retrieved
    * @return a ResponseEntity containing the result of the operation, which is a
    *         WebResponse object
    */
   @Operation(summary = "Detail Item", description = "Get detail of a item")
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
   @GetMapping(value = "/detail/{itemID}")
   public ResponseEntity<WebResponse<Object>> findCustomer(
         @Parameter(name = "itemID", description = "the ID of the item to be retrieved", required = true) @PathVariable("itemID") Integer itemID) {
      return itemService.findItem(itemID);
   }

   /**
    * Delete an existing item.
    *
    * @param itemID the ID of the item to be deleted
    * @return the response entity containing the result of the operation, which is
    *         a WebResponse object
    */
   @Operation(summary = "Delete Item", description = "Delete an existing item")
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
   @DeleteMapping(value = "/delete/{itemID}")
   public ResponseEntity<WebResponse<Object>> deleteCustomer(
         @Parameter(name = "itemID", description = "the ID of the item to be deleted", required = true) @PathVariable("itemID") Integer itemID) {
      return itemService.deleteItem(itemID);
   }

   /**
    * Get list of items.
    *
    * @param pageNumber    PageNumber of items list
    * @param pageSize      PageSize of items list
    * @param sortDirection Sort direction of items list
    * @param itemName      itemName filter of items list (optional)
    * @param itemCode      itemCode filter of items list (optional)
    * @param isAvailable   isAvailable filter of items list (optional)
    * @return ResponseEntity containing the result of the operation, which is a
    *         WebResponse object
    */
   @Operation(summary = "List Items", description = "Get list of items")
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
         @Parameter(name = "pageNumber", description = "PageNumber of items list", required = true) @RequestParam("pageNumber") String pageNumber,
         @Parameter(name = "pageSize", description = "PageSize of items list", required = true) @RequestParam("pageSize") String pageSize,
         @Parameter(name = "sortDirection", description = "Sort direction of items list", required = true) @RequestParam("sortDirection") String sortDirection,
         @Parameter(name = "itemName", description = "itemName filter of items list (optional)", required = false) @RequestParam(value = "itemName", required = false) String itemName,
         @Parameter(name = "itemCode", description = "itemCode filter of items list (optional)", required = false) @RequestParam(value = "itemCode", required = false) String itemCode,
         @Parameter(name = "isAvailable", description = "isAvailable filter of items list (optional)", required = false) @RequestParam(value = "isAvailable", required = false) Boolean isAvailable) {

      return itemService.getItem(GetListItemRequest.builder()
            .pageNumber(pageNumber)
            .pageSize(pageSize)
            .sortDirection(sortDirection)
            .itemName(itemName)
            .itemCode(itemCode)
            .isAvailable(isAvailable)
            .build());
   }

   @Operation(summary = "Item Report", description = "Print list of items as pdf file")
   @GetMapping(value = "/report")
   public void itemReport(
         @Parameter(name = "name", description = "Name of item (optional)", required = false) @RequestParam(value = "name", required = false) String name,
         @Parameter(name = "code", description = "Code of item (optional)", required = false) @RequestParam(value = "code", required = false) String code,
         @Parameter(name = "isAvailable", description = "Is active status filter of items list (optional)", required = false) @RequestParam(value = "isAvailable", required = false) Boolean isAvailable

   ) throws IOException, SQLException, JRException {
      InputStream itemListReport = new ClassPathResource("report/Item_List.jasper").getInputStream();
      Map<String, Object> parameters = new HashMap<>();
      if (name != null) {
         parameters.put("name", name.toUpperCase());
      }
      if (code != null) {
         parameters.put("code", code.toUpperCase());
      }
      if (isAvailable != null) {
         parameters.put("isAvailable", isAvailable);
      }
      JasperPrint jasperPrint = reportService.generateJasperPrint(itemListReport, parameters);

      response.setContentType(MediaType.APPLICATION_PDF.toString());
      response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
            String.format("attachment; filename=item-%s.pdf", Instant.now().toEpochMilli()));
      JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
   }
}
