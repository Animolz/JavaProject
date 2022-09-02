/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhn.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lhn.formatter.UserFormatter;
import com.lhn.validator.PassValidator;
import com.lhn.validator.UserValidator;
import com.lhn.validator.WebAppValidator;
import java.util.HashSet;
import java.util.Set;
import org.apache.tiles.request.freemarker.servlet.WebappClassTemplateLoader;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Admin
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = { 
    "com.lhn.controller",
    "com.lhn.repository",
    "com.lhn.service",
    "com.lhn.validator"
})
public class AppContextConfig implements WebMvcConfigurer{
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configure){
        configure.enable();
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        
        return resolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/pics/**").addResourceLocations("/resources/media/pics/");
    }
    
    @Bean
    public Cloudinary cloudinary(){
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                                        "cloud_name", "dyhp6kio1",
                                        "api_key", "572637772812827",
                                        "api_secret", "6dP0aWhskyxg2uodwwYgSfpahkA",
                                        "secure", true));
        
        return c;
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        
        return resolver;
    }
    
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resource 
        = new ResourceBundleMessageSource();
        resource.setBasename("message");
        return resource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean v = new LocalValidatorFactoryBean();
        v.setValidationMessageSource(messageSource());
       
        return v;
    }
    
    @Override
    public Validator getValidator() {
       return validator();
    }
    
        
    @Bean
    public WebAppValidator userValidator(){
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UserValidator());
        springValidators.add(new PassValidator());
        
        WebAppValidator w = new WebAppValidator();
        w.setSpringValidators(springValidators);
        
        return w;
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addFormatter(new UserFormatter());
    }
}
