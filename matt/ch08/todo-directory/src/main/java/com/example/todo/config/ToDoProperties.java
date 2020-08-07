package com.example.todo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "todo.authentication")
public class ToDoProperties {
	
    private String findByEmailUri;
    private String username;
    private String password;
}
