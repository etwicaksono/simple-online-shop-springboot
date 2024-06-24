package com.etwicaksono.simpleonlineshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.etwicaksono.simpleonlineshop.dto.WebResponse;
import com.etwicaksono.simpleonlineshop.utils.ResponseUtil;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorController {
   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<WebResponse<Object>> handle(IllegalArgumentException e) {
      return ResponseUtil.error400Response(e.getMessage());
   }

   @ExceptionHandler(NoResourceFoundException.class)
   public ResponseEntity<WebResponse<Object>> handle(NoResourceFoundException e) {
      return ResponseUtil.error404Response(e.getMessage());
   }

   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<WebResponse<Object>> handle(ConstraintViolationException e) {
      return ResponseUtil.errorValidationResponse(e.getMessage());
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<WebResponse<Object>> handle(Exception e) {
      log.error(e.getMessage());
      e.printStackTrace();
      return ResponseUtil.error500Response(e.getMessage());
   }

   @ExceptionHandler(MissingServletRequestParameterException.class)
   public ResponseEntity<WebResponse<Object>> handle(MissingServletRequestParameterException e) {
      return ResponseUtil.error400Response(e.getMessage());
   }
}
