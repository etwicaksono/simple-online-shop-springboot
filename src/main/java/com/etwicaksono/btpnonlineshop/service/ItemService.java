package com.etwicaksono.btpnonlineshop.service;

import org.springframework.http.ResponseEntity;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;
import com.etwicaksono.btpnonlineshop.dto.item.CreateItemRequest;
import com.etwicaksono.btpnonlineshop.dto.item.GetListItemRequest;
import com.etwicaksono.btpnonlineshop.dto.item.UpdateItemRequest;

public interface ItemService {
   ResponseEntity<WebResponse<Object>> createItem(CreateItemRequest request);

   ResponseEntity<WebResponse<Object>> updateItem(UpdateItemRequest request);

   ResponseEntity<WebResponse<Object>> findItem(Integer itemID);

   ResponseEntity<WebResponse<Object>> deleteItem(Integer itemID);

   ResponseEntity<WebResponse<Object>> getItem(GetListItemRequest request);

}
