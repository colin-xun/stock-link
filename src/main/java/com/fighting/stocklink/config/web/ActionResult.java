package com.fighting.stocklink.config.web;

/**
 * @author： colin_xun@163.com
 * @create: 2018-12-16 19:07
 * @description:
 */
public class ActionResult<T> {

    public String code;
    private String msg;
    private T data;

    public ActionResult() {
    }

    public ActionResult(String code) {
        this(code,null, null);
    }

    public ActionResult(String code, String msg) {
        this(code,msg, null);
    }

    public ActionResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ActionResult<T> success(T data, String msg){
        return new ActionResult<>(ResultCode.SUCCESS, msg, data);
    }

    public static <T> ActionResult<T> success(T data){
        return new ActionResult<>(ResultCode.SUCCESS, null, data);
    }

    public static <T> ActionResult<T> success(String message){
        return new ActionResult<>(ResultCode.SUCCESS, message, null);
    }

    public static ActionResult fail(String s) {
        return new ActionResult(ResultCode.FAIL, s, null);
    }

    public static ActionResult missParam(String s) {
        return new ActionResult(ResultCode.MISSIONG_PARAM, s, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
