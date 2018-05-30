package cn.com.taiji.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import cn.com.taiji.domain.Person;

public class Config1 {
	

	@Value("谷宇")
	private String name;

	@Value("13611037916")
	private String phoneNum;

	@Value("aa@bb.c")
	private String mail;

	@Bean
	public Person me() {
		Person me = new Person();
		me.setName(name);
		me.setPhoneNum(phoneNum);
		me.setMail(mail);
		return me;
	}
}
