/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perpus.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author martinusadyh
 */
public class PasswordHelper {
    
    public static final String keyPassword = "4d14nD4ngg1L0v3PoR3p3r";

    public static String getPlainTextFromEncryptedText(String encryptedPassword) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(keyPassword);
        String passEncrypt = encryptedPassword.substring(4, encryptedPassword.length() - 1);

        return encryptor.decrypt(passEncrypt);
    }

    public static String getEncryptedTextFromPlainText(String plainText) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(keyPassword);
        String encryptedPassword = "ENC(" + encryptor.encrypt(plainText) + ")";
        
        return encryptedPassword;
    }
}
