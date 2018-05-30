package cn.com.taiji.config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import cn.com.taiji.BasicApplication;

@Configuration
public class Config1 {
	private static final Logger log = LoggerFactory.getLogger(BasicApplication.class);
	@Autowired
	private CounterService counterService;
	@Bean
	public ApplicationListener<ApplicationEvent> xyzListener() {
		final String HELLO_URL = "/xyz";
		
		return (ApplicationEvent event) -> {
			if (event instanceof ServletRequestHandledEvent) {
				ServletRequestHandledEvent e = (ServletRequestHandledEvent) event;
				if (e.getRequestUrl().equals(HELLO_URL))
					counterService.increment("xyz.hits");
			}
		};
	}
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propsConfig = new PropertySourcesPlaceholderConfigurer();
		propsConfig.setLocation(new ClassPathResource("git.properties"));
		propsConfig.setIgnoreResourceNotFound(true);
		propsConfig.setIgnoreUnresolvablePlaceholders(true);
		return propsConfig;
	}
	//自定义健康状态
	@Bean
	public HealthIndicator myHealth() {
		return () -> {
			//获取访问百度状态码
			int code = 200;
			try {
				URL u = new URL("http://www.baidu.com");
				HttpURLConnection uConnection = (HttpURLConnection) u.openConnection();
				code = uConnection.getResponseCode();
			} catch (MalformedURLException e) {
				System.out.println("创建url失败");
				e.printStackTrace();
				return Health.up().withDetail("Internet access success", e).build();
			} catch (IOException e) {
				System.out.println("创建HttpURLConnection失败");
				e.printStackTrace();
				return Health.down().withDetail("Internet access failed", e).build();
			}
			//大于400访问失败
			if (code >= 400) {
				return Health.down().withDetail("Internet access failed", code).build();
			} else {
				return Health.up().withDetail("Internet access success", code).build();
			}
		};
	}
}
