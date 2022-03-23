package com.script.controller;

import com.api.util.Interpreter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
/*
* logic只允许返回数据,执行脚本等逻辑任务
*
*/

@RestController
@RequestMapping("/script")
public class ScriptController {

    @RequestMapping("/run")
    public String run() throws IOException {
        //自定义脚本解释器
        Interpreter interpreter = new Interpreter();
        //获取静态资源路径
        Resource resource = new ClassPathResource(
                "/static/script/mould.py"
        );

        return interpreter.RunScript(resource.getFile().getPath());

    }
}
