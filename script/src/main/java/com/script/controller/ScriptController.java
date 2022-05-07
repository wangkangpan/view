package com.script.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.api.util.Interpreter;
import com.vo.OutData;
import com.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.spi.DirStateFactory;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @RequestMapping("/run")
    public Result<String> run(String url, String tag, String key, String value) throws IOException {
        if(url == null || url == ""){
            return new Result<>(Result.UnKnownDefault,"请检查输入参数","url:"+url);
        }
        //自定义脚本解释器
        Interpreter interpreter = new Interpreter();
        //获取静态资源路径
        Resource resource = new ClassPathResource(
                "/static/script/mould.py"
        );
        List<OutData> res = interpreter.RunScript(resource.getFile().getPath(), url, tag, key, value);
        this.exportResult(res,url);
        return new Result<>(Result.Success,"200",null);


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
