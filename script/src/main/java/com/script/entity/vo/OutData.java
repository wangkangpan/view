package com.script.entity.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;

@ExcelTarget("OutData")
public class OutData implements Serializable {

    @Excel(name = "组内容",width = 30)
    private String content;

}
