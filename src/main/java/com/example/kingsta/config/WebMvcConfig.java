package com.example.kingsta.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static int CACHE_PERIOD = 60*10*6;
    private static String uploadFolder = "C:/Users/wogud2/upload/";

//    @Value("${file.path}")
//    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/upload/**")
                .addResourceLocations(uploadFolder)
                .setCachePeriod(CACHE_PERIOD)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
