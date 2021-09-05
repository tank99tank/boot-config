package com.test.builder;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Configuration
@ConfigurationProperties("test.props")
@RefreshScope
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TestProps {
	
	private String testValue;
}