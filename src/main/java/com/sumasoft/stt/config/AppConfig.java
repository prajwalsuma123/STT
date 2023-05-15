package com.sumasoft.stt.config;

import org.springframework.stereotype.Component;


//@Configuration
//@ConfigurationProperties(prefix = "myapp")
@Component
public class AppConfig {

//    @Value("${myapp.name}")
    String name;
//    @Value("${myapp.version}")
    String version;
//    @Value("${myapp.build}")
    int build;

//    public AppConfig(@Value("${myapp.name}") String name){
//        this.name=name;
//    }
    

    
    public AppConfig(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return version;
    }

    public void setUsername(String version) {
        this.version = version;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }
}
