package com.givit.dao;

import java.util.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.io.Serializable;
import java.security.SecureRandom;
import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class UserDao implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private long userId;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;
    
    @Column(name = "id_type")
    private String id_type;
    
    @Column(name = "id_name")
    private String id_name;
    
    @Column(name = "last_login_time")
    private Timestamp last_login_time;

    public UserDao() {}

    public UserDao(long userId) {

        this.userId = userId;
    }

    public UserDao(String username, String password) {
        this.username = username;
        this.password = password;
    }

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
    
    public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public String getId_name() {
		return id_name;
	}

	public void setId_name(String id_name) {
		this.id_name = id_name;
	}

	public Timestamp getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Timestamp last_login_time) {
		this.last_login_time = last_login_time;
	}
    // the following code is for password hashing ...



	private static final int iterations = 20*1000;
    private static final int saltLen = 8;
    private static final int desiredKeyLen = 64;

    public static String getSaltedHash(String password) throws Exception {
//        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
      //  return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
    	return Base64.getEncoder().encodeToString(password.getBytes());
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
//        String[] saltAndPass = stored.split("\\$");
//        if (saltAndPass.length != 2) {
//            throw new IllegalStateException(
//                    "The stored password have the form 'salt$hash'");
//        }
//        String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndPass[1]));
//        System.out.println("Hash of Input: " + hashOfInput);
        byte[] checkResult = Base64.getDecoder().decode(stored);
        String s = new String(checkResult);
        return s.equals(password);
    }
   
}
