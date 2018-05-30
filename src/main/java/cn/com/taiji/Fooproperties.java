package cn.com.taiji;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
@Data
@ConfigurationProperties(prefix="foo")
public class Fooproperties {
	private String name;
	private Integer age;
	private List<String> hobby;
	public Fooproperties(String name, Integer age, List<String> hobby) {
		this.name = name;
		this.age = age;
		this.hobby = hobby;
	}
	
}
