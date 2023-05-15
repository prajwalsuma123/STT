package com.sumasoft.stt.helper;

import com.sumasoft.stt.config.AppConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class helper {
    
    @Bean
    public AppConfig getAppconfig()
    {
        return new AppConfig();
    }
    
}


