package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
       UserDetails jamesbond = User.builder()
                .username("jamesbond")
                .password(passwordEncoder.encode("password"))
               .authorities(ApplicationUserRole.STUDENT.getGrantedAuthority())
               //.roles(ApplicationUserRole.STUDENT.name())
               .build();
        UserDetails ribesh =User.builder()
               .username("ribesh")
               .password(passwordEncoder.encode("password"))
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthority())
               //.roles(ApplicationUserRole.ADMIN.name())
               .build();
        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password"))
                .authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthority())
                //.roles(ApplicationUserRole.ADMINTRAINEE.name())
                .build();
       return new InMemoryUserDetailsManager(jamesbond,ribesh,tom);
    }
}
