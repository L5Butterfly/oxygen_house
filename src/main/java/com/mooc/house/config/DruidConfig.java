package com.mooc.house.config;

import java.sql.SQLException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;

/**
 * @package: com.mooc.house.config
 * @file: DruidConfig.java
 * @description: TODO
 * @author: empathy
 * @date: 2018年10月5日 上午11:24:17
 * @version: v1.0
 */

@Configuration
public class DruidConfig {
	
	/**
	 * druid 配置数据绑定
	 * @param statFilter
	 * @return
	 * @throws SQLException
	 */
	@ConfigurationProperties(prefix="spring.druid")
	@Bean(initMethod="init",destroyMethod="close")
	public DruidDataSource dataSource(Filter statFilter) throws SQLException{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
		return dataSource;
	}
	
	/**
	 * 慢sql语句配置过滤
	 * @return
	 */
	@Bean
	public Filter statFilter(){
		StatFilter filter = new StatFilter();
		filter.setSlowSqlMillis(1);
		filter.setLogSlowSql(true);
		filter.setMergeSql(true);
		return filter;
	}
	
	
	/**
	 * druid 监控平台
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	}

}
