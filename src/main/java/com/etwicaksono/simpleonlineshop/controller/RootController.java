package com.etwicaksono.simpleonlineshop.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class RootController {
   @GetMapping("/")
   public ModelAndView rootHandler() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("welcome");
      return modelAndView;
   }

}
