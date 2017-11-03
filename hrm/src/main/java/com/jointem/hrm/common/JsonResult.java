package com.jointem.hrm.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by zhanglu on 2017/7/28.
 */
public class JsonResult {
    private String code;
    private String msg;
    private Object data;

    public JsonResult(){
    	
    }
    public JsonResult(Object data){
    	this.data = data;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    public Object getData() {
        return data;
    }
    public String toJson(){
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
    }
}
