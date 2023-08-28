package com.weijia.im.common;

import com.weijia.im.common.exception.ApplicationExceptionEnum;

public enum BaseErrorCode implements ApplicationExceptionEnum {

    SUCCESS(200,"success"),
    SYSTEM_ERROR(9000,"服务器内部错误"),
    PARAMETER_ERROR(9001,"参数错误");
    private int code;
    private String error;

     BaseErrorCode(int code, String error) {
        this.code = code;
        this.error = error;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getError() {
        return null;
    }
}
