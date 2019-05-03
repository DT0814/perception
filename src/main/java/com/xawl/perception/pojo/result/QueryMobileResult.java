package com.xawl.perception.pojo.result;

public class QueryMobileResult {
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

    public QueryMobileResult(int retCode) {
        this.retCode = retCode;
    }

    public static QueryMobileResult getInstance(int retCode) {
        return new QueryMobileResult(retCode);
    }

    public QueryMobileResult() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QueryMobileResult{" +
                "retCode=" + retCode +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
