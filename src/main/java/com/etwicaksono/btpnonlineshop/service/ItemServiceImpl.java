package com.etwicaksono.btpnonlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.item.CreateItemRequest;
import com.etwicaksono.btpnonlineshop.dto.item.GetListItemRequest;
import com.etwicaksono.btpnonlineshop.dto.item.ItemDto;
import com.etwicaksono.btpnonlineshop.dto.item.UpdateItemRequest;
import com.etwicaksono.btpnonlineshop.entity.ItemEntity;
import com.etwicaksono.btpnonlineshop.repository.ItemRepository;
import com.etwicaksono.btpnonlineshop.utils.ResponseUtil;

import lombok.extern.slf4j.Slf4j;
import java.util.Locale;

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
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'updateItem'");
   }

   @Override
   public ResponseEntity<WebResponse<Object>> findItem(Integer itemID) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'findItem'");
   }

   @Override
   public ResponseEntity<WebResponse<Object>> deleteItem(Integer itemID) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'deleteItem'");
   }

   @Override
   public ResponseEntity<WebResponse<Object>> getItem(GetListItemRequest request) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getItem'");
   }

}
