package com.etwicaksono.btpnonlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;

import com.etwicaksono.btpnonlineshop.dto.Pagination;
import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.customer.CustomerDto;
import com.etwicaksono.btpnonlineshop.dto.item.CreateItemRequest;
import com.etwicaksono.btpnonlineshop.dto.item.GetListItemRequest;
import com.etwicaksono.btpnonlineshop.dto.item.ItemDto;
import com.etwicaksono.btpnonlineshop.dto.item.UpdateItemRequest;
import com.etwicaksono.btpnonlineshop.entity.CustomerEntity;
import com.etwicaksono.btpnonlineshop.entity.ItemEntity;
import com.etwicaksono.btpnonlineshop.repository.ItemRepository;
import com.etwicaksono.btpnonlineshop.service.specification.CustomerSpecification;
import com.etwicaksono.btpnonlineshop.service.specification.ItemSpecification;
import com.etwicaksono.btpnonlineshop.utils.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

   @Autowired
   private ItemRepository itemRepository;

   @Autowired
   private MessageSource messageSource;

   @Autowired
   private ValidationService validator;

   @Override
   public ResponseEntity<WebResponse<Object>> createItem(CreateItemRequest request) {
      validator.validate(request);
      try {
         if (itemRepository.existsByItemsCode(request.getCode())) {
            return ResponseUtil
                  .error400Response(
                        messageSource.getMessage("item.validation.code.isExist", null, Locale.getDefault()));
         }

         ItemEntity item = ItemEntity.builder()
               .itemsName(request.getName())
               .itemsCode(request.getCode())
               .price(request.getPrice())
               .isAvailable(request.getIsAvailable())
               .stock(request.getStock())
               .build();

         itemRepository.save(item);

         ItemDto result = ItemDto
               .builder()
               .itemsID(item.getItemsID())
               .itemsName(item.getItemsName())
               .itemsCode(item.getItemsCode())
               .stock(item.getStock())
               .price(item.getPrice())
               .isAvailable(item.getIsAvailable())
               .lastReStock(item.getLastRestock())
               .build();

         String messageTemplate = messageSource.getMessage("item.created.success", null, Locale.getDefault());
         String message = MessageFormat.format(messageTemplate, request.getCode());

         return ResponseUtil.success200Response(message, result);
      } catch (Exception e) {
         log.error(e.getMessage());
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> updateItem(UpdateItemRequest request) {
      validator.validate(request);
      try {
         LocalDate lastReStock = null;
         Integer itemsID = request.getItemsID();
         Optional<ItemEntity> existingItem = itemRepository.findById(itemsID);
         if (!existingItem.isPresent()) {
            return ResponseUtil.error400Response(
                  messageSource.getMessage("item.validation.itemsID.invalid", null, Locale.getDefault()));
         }
         lastReStock = existingItem.get().getLastRestock();

         if (itemRepository.existsByItemsCodeAndItemsIDNot(request.getCode(), itemsID)) {
            return ResponseUtil
                  .error400Response(
                        messageSource.getMessage("item.validation.code.isExist", null, Locale.getDefault()));
         }

         if (request.getLastReStock() != null) {
            lastReStock = request.getLastReStock();
         }

         itemRepository.updateItem(request.getName(), request.getCode(), request.getStock(), request.getPrice(),
               request.getIsAvailable(), lastReStock, itemsID);

         ItemDto result = ItemDto
               .builder()
               .itemsID(itemsID)
               .itemsName(request.getName())
               .itemsCode(request.getCode())
               .stock(request.getStock())
               .price(request.getPrice())
               .isAvailable(request.getIsAvailable())
               .lastReStock(lastReStock)
               .build();

         String messageTemplate = messageSource.getMessage("item.updated.success", null, Locale.getDefault());
         String message = MessageFormat.format(messageTemplate, request.getCode());

         return ResponseUtil.success200Response(message, result);
      } catch (Exception e) {
         log.error(e.getMessage());
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> findItem(Integer itemID) {
      try {
         Optional<ItemEntity> existingItem = itemRepository.findById(itemID);
         if (!existingItem.isPresent()) {
            return ResponseUtil
                  .error400Response(
                        messageSource.getMessage("item.validation.itemsID.invalid", null, Locale.getDefault()));
         }

         ItemDto result = ItemDto
               .builder()
               .itemsID(existingItem.get().getItemsID())
               .itemsName(existingItem.get().getItemsName())
               .itemsCode(existingItem.get().getItemsCode())
               .stock(existingItem.get().getStock())
               .price(existingItem.get().getPrice())
               .isAvailable(existingItem.get().getIsAvailable())
               .lastReStock(existingItem.get().getLastRestock())
               .build();

         String messageTemplate = messageSource.getMessage("item.retrieved.success", null, Locale.getDefault());
         String message = MessageFormat.format(messageTemplate, existingItem.get().getItemsCode());

         return ResponseUtil.success200Response(message, result);
      } catch (Exception e) {
         log.error(e.getMessage());
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> deleteItem(Integer itemID) {
      try {
         Optional<ItemEntity> existingItem = itemRepository.findById(itemID);
         if (!existingItem.isPresent()) {
            return ResponseUtil
                  .error400Response(
                        messageSource.getMessage("item.validation.itemsID.invalid", null, Locale.getDefault()));
         }

         itemRepository.deleteById(itemID);

         String messageTemplate = messageSource.getMessage("item.deleted.success", null, Locale.getDefault());
         String message = MessageFormat.format(messageTemplate, existingItem.get().getItemsCode());

         return ResponseUtil.success200Response(message, null);
      } catch (Exception e) {
         log.error(e.getMessage());
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

   @Override
   public ResponseEntity<WebResponse<Object>> getItem(GetListItemRequest request) {
      validator.validate(request);
      try {
         int pageNumber = Integer.parseInt(request.getPageNumber());
         int pageSize = Integer.parseInt(request.getPageSize());
         List<ItemDto> items = new ArrayList<>();
         Sort.Direction sortDirection = request.getSortDirection()
               .equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
         ItemSpecification itemSpecification = new ItemSpecification(request);
         Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, "itemsID"));
         Page<ItemEntity> itemPage = itemRepository.findAll(itemSpecification, pageable);
         for (ItemEntity item : itemPage) {
            items.add(ItemDto
                  .builder()
                  .itemsID(item.getItemsID())
                  .itemsName(item.getItemsName())
                  .itemsCode(item.getItemsCode())
                  .stock(item.getStock())
                  .price(item.getPrice())
                  .isAvailable(item.getIsAvailable())
                  .lastReStock(item.getLastRestock())
                  .build());
         }

         Pagination<List<ItemDto>> result = Pagination
               .<List<ItemDto>>builder()
               .data(items)
               .totalPage(itemPage.getTotalPages())
               .totalItems(itemPage.getTotalElements())
               .build();

         String message = messageSource.getMessage("items.retrieved.success", null, Locale.getDefault());
         return ResponseUtil.success200Response(message, result);
      } catch (Exception e) {
         log.error(e.getMessage());
         return ResponseUtil.error500Response(e.getMessage());
      }
   }

}
