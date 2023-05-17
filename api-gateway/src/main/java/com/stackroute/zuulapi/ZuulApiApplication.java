package com.stackroute.zuulapi;

import com.stackroute.zuulapi.pre.SimpleFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiApplication.class, args);
	}

	@Bean
	@Autowired
	public SimpleFilter simpleFilter(){
		return new SimpleFilter();
	}
}
