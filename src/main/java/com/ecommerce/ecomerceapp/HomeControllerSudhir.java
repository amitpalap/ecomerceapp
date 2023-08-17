package com.ecommerce.ecomerceapp;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeControllerSudhir {

    @GetMapping("/hello")
    public String hello(ModelMap modelMap, HttpServletRequest httpServletRequest) {
        return "index";
    }
}
