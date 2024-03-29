package com.lotes.lotesbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
    final Environment environment;
    
    public WebConfig(Environment environment) {
      this.environment = environment;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE"); ;
    }
    
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
      String location = environment.getProperty("app.file.resource-mapping");
      //https://blog.tericcabrel.com/upload-a-file-to-a-server-with-springboot/
      registry.addResourceHandler("/docfiles/**").addResourceLocations(location);
    }
}
