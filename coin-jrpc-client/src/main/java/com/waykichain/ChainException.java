package com.waykichain;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * Created by yehuan on 2019/6/4
 */

public class ChainException extends RuntimeException {

    private String message ;

    private int code;

    public ChainException(Integer code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
    public ChainException(ErrorInfo errorInfo){
        this(errorInfo.getCode(), errorInfo.getMessage()) ;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
