package com.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping({"/", "/load"})
    public String toIndex(){
        return "html/index";
    }

    @GetMapping("/home")
    public String toHome(){
        return "html/home";
    }

    @GetMapping("/comments")
    public String toComments(){
        return "html/comments";
    }

    @GetMapping("/user/person")
    public String toPerson(){
        return "html/person";
    }

    @GetMapping("/Item")
    public String toItem(){
        return "html/Item";
    }


    @GetMapping("/404")
    public String toEorror(){
        return "html/404";
    }




}
