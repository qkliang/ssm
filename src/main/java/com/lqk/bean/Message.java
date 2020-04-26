package com.lqk.bean;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private Integer codeStatus;
    private String codeMsg;
    private Map<String, Object> extend = new HashMap<String, Object>(16);

    public static Message success() {
        Message result = new Message();
        result.setCodeStatus(100);
        result.setCodeMsg("处理成功");
        return result;
    }
    public static Message fail(){
        Message result = new Message();
        result.setCodeStatus(200);
        result.setCodeMsg("处理失败");
        return result;
    }
    public Message add(String Key,Object value){
        this.getExtend().put(Key,value);
        return this;
    }
    public Integer getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        this.codeMsg = codeMsg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
