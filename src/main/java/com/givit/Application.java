package com.givit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
        "com.givit.model.second",
        "com.givit.dao"})
@EnableTransactionManagement
@SpringBootApplication
@EntityScan({"com.givit.model.master", "com.givit.dao"})
public class Application extends SpringBootServletInitializer {
	 @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(Application.class);
	    }
    public static void main(String[] args) {SpringApplication.run(Application.class, args); }
   
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter = 
            new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }
}
