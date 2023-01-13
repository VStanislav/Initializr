package com.voronkov.Initializr.config;

import com.voronkov.Initializr.service.UserServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
//@RequestMapping("api/v1/products")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserServise userServise;

    protected void configure(HttpSecurity http) throws Exception{
        log.info("Dao Authentication Provider");
        http.authorizeRequests()
                .antMatchers("/api/v1/products/order/**").authenticated()
                .antMatchers("/admin").hasAnyAuthority("SUPERADMIN")
                .antMatchers("/api/v1/products/base/**").hasAnyAuthority("WORKWITHPRODUCTS")
                //право работать с базой продуктов
                .antMatchers("/api/v1/users/**").hasAnyAuthority("WORKWITHUSERS")
                //WORK_WITH_USERS не реализовал, но это право по типу роли суперадмина, для редактирования базы пользователей.
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userServise);
        return authenticationProvider;
    }
}
