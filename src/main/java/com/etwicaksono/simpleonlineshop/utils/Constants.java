package com.etwicaksono.simpleonlineshop.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Constants {

   private Constants() {
   }

   public static String getMessage(MessageSource messageSource, String key) {
      return messageSource.getMessage(key, null, Locale.getDefault());
   }

   public static final String CUSTOMER_ADDRESS_REQUIRED = "customer.address.required";
   public static final String CUSTOMER_CODE_IS_EXIST = "customer.code.isExist";
   public static final String CUSTOMER_CODE_REQUIRED = "customer.code.required";
   public static final String CUSTOMER_CREATED_SUCCESS = "customer.created.success";
   public static final String CUSTOMER_DELETED_SUCCESS = "customer.deleted.success";
   public static final String CUSTOMER_IS_ACTIVE_REQUIRED = "customer.isActive.required";
   public static final String CUSTOMER_NAME_REQUIRED = "customer.name.required";
   public static final String CUSTOMER_PHONE_IS_EXIST = "customer.phone.isExist";
   public static final String CUSTOMER_PHONE_REQUIRED = "customer.phone.required";
   public static final String CUSTOMER_RETRIEVED_SUCCESS = "customer.retrieved.success";
   public static final String CUSTOMER_UPDATED_SUCCESS = "customer.updated.success";
   public static final String CUSTOMERS_RETRIEVED_SUCCESS = "customers.retrieved.success";
   public static final String CUSTOMER_ID_INVALID = "customerID.invalid";
   public static final String CUSTOMER_ID_REQUIRED = "customerID.required";

   public static final String ITEM_CREATED_SUCCESS = "item.created.success";
   public static final String ITEM_CODE_IS_EXIST = "item.code.isExist";
   public static final String ITEM_CODE_REQUIRED = "item.code.required";
   public static final String ITEM_DELETED_SUCCESS = "item.deleted.success";
   public static final String ITEM_IS_AVAILABLE_REQUIRED = "item.isAvailable.required";
   public static final String ITEM_NAME_REQUIRED = "item.name.required";
   public static final String ITEM_PRICE_REQUIRED = "item.price.required";
   public static final String ITEM_RETRIEVED_SUCCESS = "item.retrieved.success";
   public static final String ITEM_STOCK_REQUIRED = "item.stock.required";
   public static final String ITEM_UPDATED_SUCCESS = "item.updated.success";
   public static final String ITEMS_RETRIEVED_SUCCESS = "items.retrieved.success";
   public static final String ITEMS_ID_INVALID = "itemsID.invalid";
   public static final String ITEMS_ID_REQUIRED = "itemsID.required";

   public static final String ORDER_CREATED_SUCCESS = "order.created.success";
   public static final String ORDER_QUANTITY_REQUIRED = "order.quantity.required";
   public static final String ORDER_RETRIEVED_SUCCESS = "order.retrieved.success";
   public static final String ORDER_UPDATED_SUCCESS = "order.updated.success";
   public static final String ORDER_ID_INVALID = "orderID.invalid";
   public static final String ORDER_ID_REQUIRED = "orderID.required";
   public static final String ORDERS_RETRIEVED_SUCCESS = "orders.retrieved.success";

   public static final String PAGE_NUMBER_REQUIRED = "pageNumber.required";
   public static final String PAGE_SIZE_REQUIRED = "pageSize.required";
   public static final String QUANTITY_INVALID = "quantity.invalid";
   public static final String SORT_DIRECTION_REQUIRED = "sortDirection.required";

   // Errors
   public static final String ERROR_VALIDATION = "VALIDATION ERROR";
}
