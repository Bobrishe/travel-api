package com.alexki.authapi.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "data-api")
public class DataApiProperties {
    private String url;
    private String token;
}
