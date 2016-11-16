package com.pureilab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
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
        "com.pureilab.controller.master",
        "com.pureilab.security",
        "com.pureilab.model.master",
        "com.pureilab.model.second" })
@EnableTransactionManagement
@SpringBootApplication
public class Application {

    public static void main(String[] args) {SpringApplication.run(Application.class, args); }

}
