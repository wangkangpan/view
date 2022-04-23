package com.user.vo;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Result<T> {

    public static final String Success = "200";
    public static final String UnKnownDefault = "500";
    public static final String DataBaseDefault = "501";
    public static final String ProgramDefault = "502";
    public static final String VerifyEmailMessageDefault = "503";


    String code;
    String value;
    T object;
    public Result(String code, String value,T object){
        this.setCode(code);
        this.setValue(value);
        this.setObject(object);
    }

    public String toJson(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("code", code);
        jsonObject.put("value", value);

        return jsonObject.toString();
    }


}
