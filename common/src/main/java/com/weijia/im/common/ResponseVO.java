package com.weijia.im.common;

import com.weijia.im.common.exception.ApplicationExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一的返回类，service层返回的

@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> {
//    同意返回类
//    code，msg，data
    private int code;
    private String msg;
    private T data;


    public static ResponseVO successResponse(Object data) {
        return new ResponseVO(200, "success", data);
    }

    public static ResponseVO successResponse() {
        return new ResponseVO(200, "success");
    }

    public static ResponseVO errorResponse() {
        return new ResponseVO(500, "系统内部异常");
    }

    public static ResponseVO errorResponse(int code, String msg) {
        return new ResponseVO(code, msg);
    }

    public static ResponseVO errorResponse(ApplicationExceptionEnum enums) {
        return new ResponseVO(enums.getCode(), enums.getError());
    }

    public boolean isOk(){
        return this.code == 200;
    }


    public ResponseVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
//		this.data = null;
    }
    public ResponseVO(int code, String msg,T data) {
        this.code = code;
        this.msg = msg;
		this.data = data;
    }
    public ResponseVO success(){
        this.code = 200;
        this.msg = "success";
        return this;
    }

    public ResponseVO success(T data){
        this.code = 200;
        this.msg = "success";
        this.data = data;
        return this;
    }

}
