package com.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@ExcelTarget("OutData")
public class OutData implements Serializable {
    @Excel(name = "标签",width = 10)
    private String tag;
    @Excel(name = "属性名",width = 10)
    private String key;
    @Excel(name = "属性值",width = 10)
    private String attr;
    @Excel(name = "组内容",width = 30)
    private String content;

}
