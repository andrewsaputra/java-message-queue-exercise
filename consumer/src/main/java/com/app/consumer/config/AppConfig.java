package com.app.consumer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class AppConfig {
    private String imageDir;
    private String compressedImageDir;
    private float compressionQuality;
}
