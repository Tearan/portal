package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by marta on 02.07.17.
 */

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/login").setViewName("login");
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        }

}