package com.sed.productmanagement.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource(value = "classpath:config.properties")
public class Config {
    private Environment environment;

    public Config(Environment environment) {
        this.environment = environment;
    }

    public int getProductBatchSize() {
        return environment.getRequiredProperty("product.batch.size", Integer.class);
    }

    public int getCommentBatchSize() {
        return environment.getRequiredProperty("comment.batch.size", Integer.class);
    }

    public int getCommentShortListSize() {
        return environment.getRequiredProperty("comment.short.list.size", Integer.class);
    }

    public String getFileServerBaseUrl() {
        return environment.getRequiredProperty("file.server.base.url");
    }

    public String getProductBaseUrl() {
        return environment.getRequiredProperty("product.base.url");
    }
}
