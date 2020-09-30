package com.eteh.eteh;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.activation.DataSource;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DbApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DbApplication.class, args);
	}


	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
		return applicationBuilder.sources(DbApplication.class);
	}


//
//	@Bean
//	public LocalSessionFactoryBean sessionFactoryBean(DataSource  dataSource){
//
//		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//		sessionFactoryBean.setDataSource((javax.sql.DataSource) dataSource);
//		sessionFactoryBean.setPackagesToScan(new String[]{"D:\\eteh\\src\\main\\java\\com\\eteh\\eteh\\models"});
//		Properties properties = new Properties();
//		properties.setProperty("dialect","org.hibernate.dialect.MySQL8Dialect");
//		sessionFactoryBean.setHibernateProperties(properties);
//		return sessionFactoryBean;
//	}


}

