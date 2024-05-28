package com.etwicaksono.btpnonlineshop.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class Constants {

   @Autowired
   private static MessageSource messageSource;

   private Constants() {
      // Prevent instantiation
   }

   private static String getMessage(String key) {
      return messageSource.getMessage(key, null, Locale.getDefault());
   }

   public static final String CUSTOMER_CREATED_SUCCESS = getMessage("customer.created.success");
   public static final String CUSTOMER_DELETED_SUCCESS = getMessage("customer.deleted.success");
   public static final String CUSTOMER_RETRIEVED_SUCCESS = getMessage("customer.retrieved.success");
   public static final String CUSTOMER_UPDATED_SUCCESS = getMessage("customer.updated.success");
   public static final String CUSTOMER_VALIDATION_ADDRESS_REQUIRED = getMessage("customer.validation.address.required");
   public static final String CUSTOMER_VALIDATION_CODE_REQUIRED = getMessage("customer.validation.code.required");
   public static final String CUSTOMER_VALIDATION_CODE_IS_EXIST = getMessage("customer.validation.code.isExist");
   public static final String CUSTOMER_VALIDATION_CUSTOMER_ID_INVALID = getMessage(
         "customer.validation.customerID.invalid");
   public static final String CUSTOMER_VALIDATION_IS_ACTIVE_REQUIRED = getMessage(
         "customer.validation.isActive.required");
   public static final String CUSTOMER_VALIDATION_NAME_REQUIRED = getMessage("customer.validation.name.required");
   public static final String CUSTOMER_VALIDATION_PHONE_REQUIRED = getMessage("customer.validation.phone.required");
   public static final String CUSTOMER_VALIDATION_PHONE_IS_EXIST = getMessage("customer.validation.phone.isExist");
   public static final String CUSTOMERS_RETRIEVED_SUCCESS = getMessage("customers.retrieved.success");
   public static final String ITEM_CREATED_SUCCESS = getMessage("item.created.success");
   public static final String ITEM_DELETED_SUCCESS = getMessage("item.deleted.success");
   public static final String ITEM_RETRIEVED_SUCCESS = getMessage("item.retrieved.success");
   public static final String ITEM_UPDATED_SUCCESS = getMessage("item.updated.success");
   public static final String ITEM_VALIDATION_CODE_IS_EXIST = getMessage("item.validation.code.isExist");
   public static final String ITEM_VALIDATION_CODE_REQUIRED = getMessage("item.validation.code.required");
   public static final String ITEM_VALIDATION_IS_AVAILABLE_REQUIRED = getMessage(
         "item.validation.isAvailable.required");
   public static final String ITEM_VALIDATION_ITEMS_ID_INVALID = getMessage("item.validation.itemsID.invalid");
   public static final String ITEM_VALIDATION_ITEMS_ID_REQUIRED = getMessage("item.validation.itemsID.required");
   public static final String ITEM_VALIDATION_NAME_REQUIRED = getMessage("item.validation.name.required");
   public static final String ITEM_VALIDATION_PRICE_REQUIRED = getMessage("item.validation.price.required");
   public static final String ITEM_VALIDATION_STOCK_REQUIRED = getMessage("item.validation.stock.required");
   public static final String ITEMS_RETRIEVED_SUCCESS = getMessage("items.retrieved.success");
   public static final String ORDER_VALIDATION_CODE_REQUIRED = getMessage("order.validation.code.required");
   public static final String ORDER_VALIDATION_ORDER_DATE_REQUIRED = getMessage("order.validation.orderDate.required");
   public static final String ORDER_VALIDATION_ORDER_ID_REQUIRED = getMessage("order.validation.orderID.required");
   public static final String ORDER_VALIDATION_TOTAL_PRICE_REQUIRED = getMessage(
         "order.validation.totalPrice.required");
   public static final String ORDER_VALIDATION_CUSTOMER_ID_REQUIRED = getMessage(
         "order.validation.customerID.required");
   public static final String ORDER_VALIDATION_ITEMS_ID_REQUIRED = getMessage("order.validation.itemsID.required");
   public static final String ORDER_VALIDATION_QUANTITY_REQUIRED = getMessage("order.validation.quantity.required");
   public static final String PAGE_NUMBER_REQUIRED = getMessage("pageNumber.required");
   public static final String PAGE_SIZE_REQUIRED = getMessage("pageSize.required");
   public static final String SORT_DIRECTION_REQUIRED = getMessage("sortDirection.required");
}
