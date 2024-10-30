package com.demo.entity.model;

/**
 * 统一接口返回定义
 */
public class Result {
    //结果状态码
    private Integer code;
    //请求结果信息
    private String message;
    //如果是查询=>在该属性中表达
    private Object data;

    public Result() {}

    public Result(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code,String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    // 快捷方法：创建成功的结果
    public static Result success(String message, Object data) {
        return new Result(StatusCode.OK, message, data);
    }

    public static Result success(String message) {
        return success(message, null);
    }

    public static Result success(Object data) {
        return success("操作成功", data);
    }
    
    public static Result success() {
        return success("操作成功", null);
    }

    // 快捷方法：创建失败的结果
    public static Result error(int code, String message, Object data) {
        return new Result(code, message, data);
    }

    public static Result error(int code, String message) {
        return error(code, message, null);
    }
    
    public static Result error(String message,Object data) {
        return error(StatusCode.ERROR, message, data);
    }
    
    public static Result error(String message) {
        return error(StatusCode.ERROR, message);
    }  
}