package cn.com.taiji.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.taiji.Fooproperties;


@Configuration
public class TaijCongfiguration {
	@Value("${info.app.mobile}")
	private String name;
	
	@Value("${info.app.email}")
	private Integer age;

	@Value("${list}")
	private List<String> hobby;
	@Bean
	public Fooproperties foo() {
		 
		
		return new Fooproperties(name,age,hobby);
	}
}
