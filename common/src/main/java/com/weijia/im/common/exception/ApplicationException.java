package com.weijia.im.common.exception;

public class ApplicationException extends RuntimeException{
//    全局异常处理类
        private int code;
        private String error;

        public ApplicationException(int code,String msg){
                super(msg);
                this.code=code;
                this.error=msg;
        }
//        异常更新
        public  ApplicationException(ApplicationExceptionEnum exceptionEnum){
                super(exceptionEnum.getError());
                this.code=exceptionEnum.getCode();
                this.error= exceptionEnum.getError();
        }

        public int getCode() {
                return code;
        }

        public String getError() {
                return error;
        }

        @Override
        public  Throwable fillInStackTrace() {
                return this;
        }
}
