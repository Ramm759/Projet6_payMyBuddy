package com.mycompany.paymybuddy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PayMyBuddyUserDetailsService detailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("").hasAnyAuthority("USER")
                .antMatchers("").permitAll()
                .and().formLogin().loginPage("/login")  //login configuration
                .defaultSuccessUrl("")
                .and().logout()    //logout configuration
                .logoutUrl("")
                .logoutSuccessUrl("/")
                .and().exceptionHandling() //exception handling configuration
                .accessDeniedPage("");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder);
    }
}
