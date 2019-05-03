package com.xawl.perception.utils;

public class ResultCode {
    //请求操作成功状态码
    public static final int SUCCESS = 0;

    public static final int FAIL = 400;
    //请求参数检验失败
    public static final int PARAMETER_ERROR = 401;
    //请求拦截,acode错误
    public static final int ACODE_NO_EQUALS = 402;

    //接入系统请求认证失败(随机数加密密码错误)
    public static final int CONFIRM_NO_PASS_FAIL = 411;
    //退出系统失败
    public static final int LOGIN_OUT_FAIL = 412;
    //认证找不到客户端编号
    public static final int LOGIN_CONFIRM_NOT_FOUND_CLIENT = 413;
    //未请求request接口
    public static final int LOGIN_CONFIRM_NOT_REQUEST = 414;


    //管理员登陆账号为空
    public static final int ADMIN_LOGIN_ACCOUNT_NULL = 421;
    //管理员登陆账号或密码错误
    public static final int ADMIN_LOGIN_PASS_ERROT = 422;


    //找不到客户端id
    public static final int REQUEST_CLIENT_NOT_FOUND = 441;


    //添加移动端设备名字为空
    public static final int MOBILE_ADD_NAME_NULL = 451;
    //添加移动端设备账号为空
    public static final int MOBILE_ADD_ACCOUNT_NULL = 452;
    //添加移动端设备密码为空
    public static final int MOBILE_ADD_PASS_NULL = 453;

}
