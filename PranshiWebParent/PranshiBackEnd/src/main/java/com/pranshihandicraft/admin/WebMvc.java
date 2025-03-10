package com.pranshihandicraft.admin;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvc implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirName="user-photos";
		Path userPhotoDir= Paths.get(dirName);
		
		String userPhotosPath = userPhotoDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/"+dirName+"/**").addResourceLocations("file:/"+userPhotosPath+"/");
	}
}
