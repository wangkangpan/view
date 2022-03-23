package com.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

    @RequestMapping({"/", "/load"})
    public String toIndex(){
        return "html/index";
    }

    @RequestMapping("/home")
    public String toHome(){
        return "html/home";
    }






}
