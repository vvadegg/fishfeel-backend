package ru.fishfeel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.fishfeel.security.jwt.JwtConfugurer;
import ru.fishfeel.security.jwt.JwtTokenProvider;

import javax.inject.Inject;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Inject
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login", "/user/registration").permitAll()
                .antMatchers("/api/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfugurer(jwtTokenProvider));
    }
}
