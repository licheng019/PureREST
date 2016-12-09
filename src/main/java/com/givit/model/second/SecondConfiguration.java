//package com.givit.model.second;
//
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import javax.sql.DataSource;
//import javax.persistence.EntityManagerFactory;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableJpaRepositories(entityManagerFactoryRef = "secondEntityManagerFactory",
//        transactionManagerRef = "secondTransactionManager",
//        basePackages="com.givit.model.second")
//public class SecondConfiguration {
//
//    @Bean
//    @ConfigurationProperties(prefix="pure.datasource")
//    public DataSource secondaryDataSource() {
//                return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    PlatformTransactionManager secondTransactionManager() {
//        return new JpaTransactionManager(secondEntityManagerFactory().getObject());
//    }
//
//    @Bean
//    LocalContainerEntityManagerFactoryBean secondEntityManagerFactory() {
//
//        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//
//        factoryBean.setDataSource(secondaryDataSource());
//        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//        factoryBean.setPackagesToScan(SecondConfiguration.class.getPackage().getName());
//
//        return factoryBean;
//    }
//}
