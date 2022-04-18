package com.user.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Page implements Serializable {
    //当前页
    private Integer pageNo;
    //当前页数量
    private Integer pageSize;
    //计数终点
    private Integer pageEnd;
    //计数起点
    private Integer pageStart;

    public void setStartAndEnd(Integer pageNo,Integer pageSize){
        this.pageStart = (pageNo - 1) * pageSize;
        this.pageEnd = pageStart + pageSize;
    }
}
