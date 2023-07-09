package com.StudentLoginv01.StudentLoginv01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.StudentLoginv01.StudentLoginv01.service.*;

@SpringBootApplication
public class StudentLoginv01Application {
	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(StudentLoginv01Application.class, args);
	}
	
//	@Value("${allowed.origin}")
//	private String allowedOrigin;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200");
			}
		};
	}
	
	public void run(String... arg) throws Exception {
//		storageService.deleteAll();
		storageService.init();
	}

}
