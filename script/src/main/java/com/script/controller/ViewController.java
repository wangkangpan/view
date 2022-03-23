package com.script.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


/*
* view负责视图跳转逻辑、网站运行逻辑等基础功能
*
 */

@Controller
@RequestMapping("/collection")
public class ViewController {


    @RequestMapping("/home")
    public String toCollection() throws IOException {

        return  "html/collection";
    }


}
