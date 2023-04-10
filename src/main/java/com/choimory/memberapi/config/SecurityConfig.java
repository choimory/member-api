package com.choimory.memberapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        /*스프링 시큐리티 기본 로그인 비활성화*/
        //http.httpBasic().disable();
        http.formLogin().disable();

        /*스프링 시큐리티 csrf 비활성화*/
        //REST API의 경우 stateless하므로 CSRF를 비활성화하여도 괜찮다.
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
