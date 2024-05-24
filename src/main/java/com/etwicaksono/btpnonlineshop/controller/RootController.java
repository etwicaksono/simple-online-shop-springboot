package com.etwicaksono.btpnonlineshop.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class RootController {
   @RequestMapping("/")
   public String rootHanlder() {
      return "Welcome to BTPN Online Shop API";
   }

}
