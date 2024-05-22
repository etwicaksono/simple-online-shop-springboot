package com.etwicaksono.btpnonlineshop.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.etwicaksono.btpnonlineshop.dto.WebResponse;

public class ResponseUtil {
   private ResponseUtil() {
      // Prevent instantiation
   }

   /*
    * Success Response
    */
   public static ResponseEntity<WebResponse<Object>> success200Response(String message, Object data) {
      return ResponseEntity
            .ok()
            .body(WebResponse.<Object>builder()
                  .statusCode(HttpStatus.OK.value())
                  .status(HttpStatus.OK.getReasonPhrase())
                  .message(message)
                  .data(data)
                  .build());
   }

   public static ResponseEntity<WebResponse<Object>> success201Response(String message) {
      return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(WebResponse.<Object>builder()
                  .statusCode(HttpStatus.CREATED.value())
                  .status(HttpStatus.CREATED.getReasonPhrase())
                  .message(message)
                  .build());
   }

   /*
    * Error Response
    */
   public static ResponseEntity<WebResponse<Object>> errorValidationResponse(String message) {
      return ResponseEntity
            .badRequest()
            .body(WebResponse.<Object>builder()
                  .statusCode(HttpStatus.BAD_REQUEST.value())
                  .status("Error Validation")
                  .message(message)
                  .build());
   }

   public static ResponseEntity<WebResponse<Object>> error400Response(String message) {
      return ResponseEntity
            .badRequest()
            .body(WebResponse.<Object>builder()
                  .statusCode(HttpStatus.BAD_REQUEST.value())
                  .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                  .message(message)
                  .build());
   }

   public static ResponseEntity<WebResponse<Object>> error401Response(String message) {
      return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(WebResponse.<Object>builder()
                  .statusCode(HttpStatus.UNAUTHORIZED.value())
                  .status(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                  .message(message)
                  .build());
   }

   public static ResponseEntity<WebResponse<Object>> error404Response(String message) {
      return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(WebResponse.<Object>builder()
                  .statusCode(HttpStatus.NOT_FOUND.value())
                  .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                  .message(message)
                  .build());
   }

   public static ResponseEntity<WebResponse<Object>> error500Response(String message) {
      return ResponseEntity
            .internalServerError()
            .body(WebResponse.<Object>builder()
                  .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                  .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                  .message(message)
                  .build());
   }

}
