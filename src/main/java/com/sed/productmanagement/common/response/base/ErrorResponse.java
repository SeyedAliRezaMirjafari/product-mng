package com.sed.productmanagement.common.response.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse extends ResponseServiceBase {
    private String Code;
    private String Desc;

    public ErrorResponse(Result result, String code, String desc) {
        setResult(result);
        Code = code;
        Desc = desc;
    }
}
