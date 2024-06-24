package com.etwicaksono.simpleonlineshop.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class ValidationService {
   @Autowired
   private Validator validator;

   public void validate(Object object) {
      Set<ConstraintViolation<Object>> violations = validator.validate(object);
      if (!violations.isEmpty()) {
         throw new ConstraintViolationException(violations);
      }
   }

}
