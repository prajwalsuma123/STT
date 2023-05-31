package com.sumasoft.stt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {SpeachToTextApplication.class})
public class SpeachToTextApplication {

	public static final Logger logger= LoggerFactory.getLogger(SpeachToTextApplication.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext= SpringApplication.run(SpeachToTextApplication.class, args);
	}


}
