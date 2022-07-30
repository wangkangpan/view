package com.user.controller;


import com.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("rpc")
public class RpcController {

    private final RestTemplate restTemplate;

    @GetMapping("/test/{id}")
    public User test(@PathVariable String id){
        String url = "http://localhost:8082/rpc/test" + (id + 100);

        // rpc 传送需要 url,实体类(服务生产方和接收消费方约定)
        return restTemplate.getForObject(url, User.class);
    }

}
