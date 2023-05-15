package com.sumasoft.stt;

import com.sumasoft.stt.audio.AudioConfig;
import com.sumasoft.stt.audio.AudioFile;
import com.sumasoft.stt.audio.Microphone;
import com.sumasoft.stt.config.AppConfig;
import com.sumasoft.stt.user.AunthenticateUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@ComponentScan(basePackageClasses = {SpeachToTextApplication.class,AppConfig.class})
public class SpeachToTextApplication {

	public static final Logger logger= LoggerFactory.getLogger(SpeachToTextApplication.class);
	
	public static void main(String[] args) throws IOException, URISyntaxException, NoSuchAlgorithmException, LineUnavailableException, InterruptedException {
		ApplicationContext applicationContext= SpringApplication.run(SpeachToTextApplication.class, args);
		getSysteminfo();
		applicationContext.getEnvironment().getProperty("myapp.name");
		AunthenticateUser aunthenticateUser=new AunthenticateUser();

		if(aunthenticateUser.aunthenticateUser("98d0c598-eb03-11ed-a05b-0242ac120003")){
			logger.info("User Authenticated Sucessfully");

//			Microphone microphone=new Microphone();
//			microphone.startMicrophone();
			try
			{
				AudioFile audioFile=new AudioFile();
				audioFile.sendAudio();
			}
			catch (Exception e){
				
			}
			
		
		}

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
