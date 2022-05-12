package com.script.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.api.util.Interpreter;
import com.vo.OutData;
import com.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

    @GetMapping("/run")
    public void run(String url, String tag, String key, String value,HttpServletResponse response) throws IOException {

        if(url == null || url == "") {
            log.info("url参数信息为空");
            return;
        }
//        自定义脚本解释器
        Interpreter interpreter = new Interpreter();
        //获取静态资源路径
        Resource resource = new ClassPathResource(
                "/static/script/mould.py"
        );
        List<OutData> res = interpreter.RunScript(resource.getFile().getPath(), url, tag, key, value);

//        OutData outData = new OutData();
//        outData.setTag("span");
//        outData.setAttr("class");
//        outData.setKey("tag");
//        outData.setContent("这是一个测试");
//        List<OutData> res = new ArrayList<>(4);
//        res.add(outData);
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


        //带出Excel文件
    private void exportResult(List<OutData> params,String url){

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(url,"导出结果"),
                OutData.class, params);
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("result.xls");
            //导出完成
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            workbook.close();

        }catch(IOException e){
            log.error("IOException:" + e.getMessage());
        }catch (Exception e){
            log.error("Exception:" + e.getMessage());
        }
    }
}
