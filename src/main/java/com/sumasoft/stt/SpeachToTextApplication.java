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
	
	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(SpeachToTextApplication.class, args);
		getSysteminfo();
		}
		

	private static void getSysteminfo() {
		logger.info("--------------------- System Information ---------------------");
		logger.info("Operating system :"+System.getProperty("os.name"));
		logger.info("Java version :"+System. getProperty("java.version"));
		logger.info("Operating system architecture :"+System.getProperty("os.arch"));
		logger.info("Java Home :"+System.getProperty("java.home"));
		logger.info("System username :"+System.getProperty("user.name"));
		logger.info("--------------------- System Information ---------------------");

	}

}
