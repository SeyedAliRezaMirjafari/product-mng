package com.sed.productmanagement.common.response.base;

import java.io.IOException;
import java.util.Properties;

public enum Result {
    SUCCESS(0, "success"),
    UNKNOWN(1, "unknown"),
    FAILURE(2, "failure"),
    NOT_FOUND(3, "not.found"),
    ACCESS_ERROR(4, "access.error");

    private final String description;
    private final Integer status;

    private Result(int status, String description) {
        this.status = status;
        String errorMsg = Result.MessageHolder.ERROR_MESSAGE_PROPERTIES.getProperty(description);
        this.description = errorMsg != null ? errorMsg : description;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getStatus() {
        return this.status;
    }

    private static final class MessageHolder {
        private static final Properties ERROR_MESSAGE_PROPERTIES = new Properties();

        private MessageHolder() {
        }

        static {
            try {
                ERROR_MESSAGE_PROPERTIES.load(Result.class.getResourceAsStream("/error-messages.properties"));
            } catch (IOException var1) {
                throw new ExceptionInInitializerError(var1);
            }
        }
    }
}