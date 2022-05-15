package com.script.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.api.util.Interpreter;
import com.vo.OutData;
import com.vo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/*
* logic只允许返回数据,执行脚本等逻辑任务
*
*/
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/script")
public class ScriptController {

    Resource resource = new ClassPathResource(
            "/static/script/mould.py"
    );

    @GetMapping("/run")
    public void run(String url, String tag, String key, String value,HttpServletResponse response) throws IOException {

        if(url == null || url == "") {
            log.info("url参数信息为空");
            return;
        }
//        自定义脚本解释器
        Interpreter interpreter = new Interpreter();
        //获取静态资源路径

        List<OutData> res = interpreter.RunScript(resource.getFile().getPath(), url, tag, key, value);

//        OutData outData = new OutData();
//        outData.setTag("span");
//        outData.setAttr("class");
//        outData.setKey("tag");
//        outData.setContent("这是一个测试");
//        List<OutData> res = new ArrayList<>(4);
//        res.add(outData);
        export(response, res);


    }

    @GetMapping("/multi")
    public void multiRun(String url, String tag, String key, String value,HttpServletResponse response) throws IOException {
        String[] url_set = url.split(";");
        if(url_set.length > 5){
            return;
        }
        List<OutData> res = new ArrayList<>();
        Interpreter interpreter = new Interpreter();

        for(String url_single : url_set){
            res.addAll(interpreter.RunScript(resource.getFile().getPath(), url_single, tag, key, value));
        }

        export(response, res);
        return;
    }

    private void export(HttpServletResponse response, List<OutData> res) throws IOException {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),
                OutData.class, res);


        ServletOutputStream out = response.getOutputStream();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("result.xls","UTF-8"));

        workbook.write(out);

        out.flush();
        out.close();
        workbook.close();
    }



}
