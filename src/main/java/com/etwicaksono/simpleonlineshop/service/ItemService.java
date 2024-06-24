package com.etwicaksono.simpleonlineshop.service;

import org.springframework.http.ResponseEntity;

import com.etwicaksono.simpleonlineshop.dto.WebResponse;
import com.etwicaksono.simpleonlineshop.dto.item.CreateItemRequest;
import com.etwicaksono.simpleonlineshop.dto.item.GetListItemRequest;
import com.etwicaksono.simpleonlineshop.dto.item.UpdateItemRequest;

public interface ItemService {
   ResponseEntity<WebResponse<Object>> createItem(CreateItemRequest request);

   ResponseEntity<WebResponse<Object>> updateItem(UpdateItemRequest request);

   ResponseEntity<WebResponse<Object>> findItem(Integer itemID);

   ResponseEntity<WebResponse<Object>> deleteItem(Integer itemID);

   ResponseEntity<WebResponse<Object>> getItem(GetListItemRequest request);

}
