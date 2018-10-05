package com.mooc.house.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @package: com.mooc.house.filter
 * @file: FilterBeanConfig.java
 * @description: 拦截器配置文件的配置
 * @author: empathy
 * @date: 2018年10月4日 下午12:53:02
 * @version: v1.0
 */


@Configuration
public class FilterBeanConfig {
	

	/**
	 * 1.构造filter
	 * 2.配置拦截urlPattern
	 * 3.利用FilterRegistrationBean进行包装
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public FilterRegistrationBean logFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new LogFilter());
		List<String> urList = new ArrayList<String>();
		urList.add("*");
		filterRegistrationBean.setUrlPatterns(urList);
		return filterRegistrationBean;
	}

}
