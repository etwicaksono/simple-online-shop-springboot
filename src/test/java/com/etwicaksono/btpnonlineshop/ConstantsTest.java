package com.etwicaksono.btpnonlineshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import com.etwicaksono.btpnonlineshop.utils.Constants;

@SpringBootTest
public class ConstantsTest {

   @Autowired
   private Constants constants;

   @Autowired
   private MessageSource messageSource;

   @Test
   public void testMessages() {
      assertEquals("Customer created successfully",
            Constants.getMessage(messageSource, Constants.CUSTOMER_DELETED_SUCCESS));
      assertEquals("Customer deleted successfully",
            Constants.getMessage(messageSource, Constants.CUSTOMER_DELETED_SUCCESS));
   }
}