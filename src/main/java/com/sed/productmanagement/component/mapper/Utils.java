package com.sed.productmanagement.component.mapper;

import com.sed.productmanagement.config.Config;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class Utils {

    private final Config config;

    @Named("toLong")
    public Long toLong(Instant instant) {
        if (instant != null) {
            return instant.toEpochMilli();
        }
        return null;
    }

    @Named("toFileUrl")
    public String toFileUrl(String path) {
        return new StringBuilder()
                .append(config.getFileServerBaseUrl())
                .append(path)
                .toString();
    }

    @Named("toProductUrl")
    public String toProductUrl(String path) {
        return new StringBuilder()
                .append(config.getProductBaseUrl())
                .append(path)
                .toString();
    }
}
