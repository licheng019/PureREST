package com.givit.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHelper {
	  private static final Random RANDOM = new SecureRandom();
	  private static final int ITERATIONS = 10000;
	  private static final int KEY_LENGTH = 256;
	  private static byte[] salt = "SHA1PRNG".getBytes();
	  
  public static void main(String[] args) throws NoSuchAlgorithmException{
	  String password = generateRandomPassword(3);
	  String storedString = getHashedString(password);
	  System.out.println(isExpectedPassword(password, storedString));
  }
 
  /**
   * Returns a salted and hashed password using the provided hash.<br>
   * Note - side effect: the password is destroyed (the char[] is filled with zeros)
   *
   * @param password the password to be hashed
   * @param salt     a 16 bytes salt, ideally obtained with the getNextSalt method
   *
   * @return the hashed password with a pinch of salt
   */
  public static byte[] hash(char[] password) {
    PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
    Arrays.fill(password, Character.MIN_VALUE);
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      return skf.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
    } finally {
      spec.clearPassword();
    }
  }
 public static String getHashedString(String password) {
	 return Base64.getEncoder().encodeToString(hash(password.toCharArray()));
 }
  /**
   * Returns true if the given password and salt match the hashed value, false otherwise.<br>
   * Note - side effect: the password is destroyed (the char[] is filled with zeros)
   *
   * @param password     the password to check
   * @param salt         the salt used to hash the password
   * @param expectedHash the expected hashed value of the password
   *
   * @return true if the given password and salt match the hashed value, false otherwise
   */
  public static boolean isExpectedPassword(String password, String Storedpassword) {
	byte[] expectedHash = Base64.getDecoder().decode(Storedpassword);
	char[] passwordChars = password.toCharArray();
    byte[] pwdHash = hash(passwordChars);
    Arrays.fill(passwordChars, Character.MIN_VALUE);
    if (pwdHash.length != expectedHash.length) return false;
    for (int i = 0; i < pwdHash.length; i++) {
      if (pwdHash[i] != expectedHash[i]) return false;
    }
    return true;
  }

  /**
   * Generates a random password of a given length, using letters and digits.
   *
   * @param length the length of the password
   *
   * @return a random password
   */
  public static String generateRandomPassword(int length) {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int c = RANDOM.nextInt(62);
      if (c <= 9) {
        sb.append(String.valueOf(c));
      } else if (c < 36) {
        sb.append((char) ('a' + c - 10));
      } else {
        sb.append((char) ('A' + c - 36));
      }
    }
    return sb.toString();
  }
}