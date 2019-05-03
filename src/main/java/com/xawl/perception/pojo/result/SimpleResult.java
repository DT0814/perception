package com.xawl.perception.pojo.result;

public class SimpleResult {
    private int retCode;
    private String msg;
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public SimpleResult(int retCode) {
        this.retCode = retCode;
    }

    public static SimpleResult getInstance(int retCode) {
        return new SimpleResult(retCode);
    }

    public static SimpleResult Success(Object data) {
        SimpleResult simpleResult = new SimpleResult(0);
        simpleResult.setData(data);
        return simpleResult;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public SimpleResult() {
    }

    @Override
    public String toString() {
        return "SimpleResult{" +
                "retCode=" + retCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
