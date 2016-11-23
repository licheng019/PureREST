package com.givit.model.second;

import javax.persistence.*;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Julian on 9/28/2016.
 *
 * Tutorial:
 * http://blog.netgloo.com/2014/10/27/using-mysql-in-spring-boot-via-spring-data-jpa-and-hibernate/
 *
 * GitHub Sample:
 * https://github.com/netgloo/spring-boot-samples/blob/master/spring-boot-mysql-springdatajpa-hibernate/src/main/java/netgloo/models/User.java
 *
 * To run:
 *
 *
 * mvn compile
 *
 * mvn spring-boot:run
 *
 */
@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    public Country() {}

    public Country(long id) {

        this.id = id;
    }

    public Country(String code, String name) {

        this.code = code;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
