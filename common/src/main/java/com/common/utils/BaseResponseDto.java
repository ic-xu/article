package com.common.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponseDto implements Serializable {

    private static final long serialVersionUID = 8837009487120956221L;

    private Integer code;

    private Object data;

    private String msg;

    public BaseResponseDto() {
    }

    public BaseResponseDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponseDto(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static BaseResponseDto success(Object data) {
        BaseResponseDto responseDto = new BaseResponseDto(0,
                data, "操作成功");
        return responseDto;
    }

    public static BaseResponseDto successUpload(Object data) {
        BaseResponseDto responseDto = new BaseResponseDto(0,
                data, "操作成功");
        return responseDto;
    }

    public static BaseResponseDto error(int code, String errorMessage) {
        BaseResponseDto responseDto = new BaseResponseDto(code, errorMessage);
        return responseDto;
    }

    public static BaseResponseDto success() {
        return success(null);
    }

}