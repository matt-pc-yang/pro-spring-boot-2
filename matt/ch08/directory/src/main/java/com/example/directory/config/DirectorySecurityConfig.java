package com.example.directory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.directory.repository.PersonRepository;
import com.example.directory.security.DirectoryUserDetailsService;

@Configuration
public class DirectorySecurityConfig extends WebSecurityConfigurerAdapter {
	
    private PersonRepository personRepository;
    
    public DirectorySecurityConfig(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/**").hasRole("ADMIN")
                .and().httpBasic()
                .and().csrf().ignoringAntMatchers("/h2-console/**")		// don't apply CSRF protection to /h2-console
                .and().headers().frameOptions().sameOrigin();			// allow use of frame to same origin urls
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
            new DirectoryUserDetailsService(this.personRepository));
    }
    
    @Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}