package com.me.sonarqubeexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@RequestMapping(path = "encrypt")
public class EncriptionController {

    private static Cipher ecipher;
    private static Cipher dcipher;
    private static SecretKey key;

    @GetMapping("/{value}")
    public String index(@PathVariable("value") String value) throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher nCipher = NullCipher.getInstance("DES");

        if (value.isEmpty()) {
            return "Please supply valid value.";
        }

        try {
            // generate secret key using DES algorithm
            key = KeyGenerator.getInstance("DES").generateKey();
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

            String encrypted = encrypt("This is a classified message!");
            System.out.println("Encrypted: " + encrypted);
            String decrypted = decrypt(encrypted);
            System.out.println("Decrypted: " + decrypted);

            return decrypted;

        } catch (NoSuchAlgorithmException e) {
            return "No Such Algorithm:" + e.getMessage();
        } catch (NoSuchPaddingException e) {
            return "No Such Padding:" + e.getMessage();
        } catch (InvalidKeyException e) {
            return "Invalid Key:" + e.getMessage();
        }
    }

    public static String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            enc = Base64.getEncoder().encode(enc);
            return new String(enc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String str) {
        try {
            byte[] dec = Base64.getDecoder().decode(str.getBytes());
            byte[] utf8 = dcipher.doFinal(dec);

            return new String(utf8, "UTF8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
