package com.sumasoft.stt.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Getter@Setter
@ConfigurationProperties(prefix = "db")
public class AppConfig {

    private String url;
    private String username;
}
