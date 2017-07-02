package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by marta on 20.06.17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").authorities("ROLE_USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").authorities("ROLE_ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/img/**", "/js/**", "/fonts/**",
                        "/register", "/users/confirm", "/password", "/password/*")
                    .permitAll()
                .anyRequest()
                    .authenticated();

                http.formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .csrf().disable();


    }

    private void hackSetCustomAuthenticationEntryPoint(FormLoginConfigurer<HttpSecurity> configurer) {
        LoginUrlAuthenticationEntryPoint adapter = new LoginUrlAuthenticationEntryPoint("/login") {

            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException, ServletException {
                if (request.getHeader("Accept") != null && request.getHeader("Accept").contains("application/json")) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                } else {
                    super.commence(request, response, authException);
                }
            }

        };

        Field field = ReflectionUtils.findField(configurer.getClass(), "authenticationEntryPoint");
        field.setAccessible(true);
        ReflectionUtils.setField(field, configurer, adapter);
    }
}
