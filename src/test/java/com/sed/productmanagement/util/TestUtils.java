package com.sed.productmanagement.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class TestUtils {
    public static final String PRODUCT = "/product";

    public static String baseUrl(int port) {
        return "http://localhost:" + port;
    }

    public static HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Id", "user-id");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
