package com.example.demo.security;

import com.example.demo.bean.Role;
import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Created by marta on 16.07.17.
 */
@Component
@Log4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();


        try {
            User user = userRepository.findByEmail(name);

            if (user != null) {
                List<Role> authorities = user.getRoles();
                return new UsernamePasswordAuthenticationToken(
                        name, password, authorities);
            } else {
                return null;
            }

        }catch (ServiceException ex){
            log.error(ex);
            throw new AuthenticationServiceException("Authentication error", ex);
        }

    }

    @Override
    public boolean supports(Class<?> authClass) {
        return authClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
