package com.givit.model.master;

import java.util.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    public User() {}

    public User(long id) {

        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // the following code is for password hashing ...

    private static final int iterations = 20*1000;
    private static final int saltLen = 8;
    private static final int desiredKeyLen = 64;

    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
    }

    // using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
    // cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, iterations, desiredKeyLen)
        );
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /** Checks whether given plaintext password corresponds
     to a stored salted hash of the password. */
    public static boolean check(String password, String stored) throws Exception{
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException(
                    "The stored password have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPass[0]));
        System.out.println("Hash of Input: " + hashOfInput);
        return hashOfInput.equals(saltAndPass[1]);
    }

}
