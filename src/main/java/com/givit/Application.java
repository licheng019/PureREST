package com.givit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Julian on 9/28/2016.
 */
@EnableAutoConfiguration(
        exclude = { DataSourceAutoConfiguration.class
              , HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class
 /**  **/
        })
@ComponentScan(basePackages = {
        "com.givit.controller.master",
        "com.givit.security",
        "com.givit.model.master",
        "com.givit.model.second" })
@EnableTransactionManagement
@SpringBootApplication
@EntityScan("com.givit.model.master")
public class Application extends SpringBootServletInitializer {
	 @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(Application.class);
	    }
    public static void main(String[] args) {SpringApplication.run(Application.class, args); }

}
