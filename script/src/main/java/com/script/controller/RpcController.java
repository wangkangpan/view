package com.script.controller;


import com.script.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/rpc")
public class RpcController {

    // @PathVariable是从url中取值
    // @PathParam是从request取值,取值方式是?name=""&,key-value键值对方式
    @GetMapping("/test/{id}")
    public User test(@PathVariable Integer id){
        id += 1000;
        return new User(id,"rpc", "test", "123456", "1456225949@qq.com", "18320735332", new Date(), "UTF");
    }

}
