//package com.library.security;
//
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable() // Disable CSRF protection for simplicity
//            .authorizeRequests()
//                .antMatchers("/signup").permitAll() // Allow unauthenticated access to signup
//                .anyRequest().authenticated() // Require authentication for other endpoints
//            .and()
//            .httpBasic(); // Use Basic Authentication
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // Configure authentication provider (if needed)
//    }
//}
