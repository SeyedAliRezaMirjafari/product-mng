package com.sed.productmanagement.exception;

import com.sed.productmanagement.common.response.base.Result;

public class GeneralException extends Exception {
    public String errorCode;

    public String errorMessage;

    public Result result;

    public GeneralException(String message) {
        super(message);
    }

    public GeneralException(Throwable cause) {
        super(cause);
    }

    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralException(String errorCode, String errorMessage, Result result) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.result = result;
    }

    public GeneralException(Result result, String message) {
        super(message);
        this.errorCode = result.getStatus().toString();
        this.errorMessage = result.getDescription();
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
