package demos.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DemoAES {

    public static void main(String[] sss) throws Exception {

        DemoAES aes = new DemoAES();
        aes.decrypt(aes.encrypt("abc"));
    }

    /**
     * 根据密钥对指定的明文plainText进行加密.
     *
     * @param plainText 明文
     * @return 加密后的密文.
     */
    public final String encrypt(String plainText) throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed("abc".getBytes());
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(secureRandom);
        Key secretKey = generator.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] p = plainText.getBytes("UTF-8");
        byte[] result = cipher.doFinal(p);
        BigInteger bigInt = new BigInteger(1, result);
        return bigInt.toString();
    }


    /**
     * 根据密钥对指定的密文cipherText进行解密.
     *
     * @param cipherText 密文
     * @return 解密后的明文.
     */
    public final String decrypt(String cipherText) throws NoSuchAlgorithmException {
        System.out.println(cipherText);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed("abc".getBytes());
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(secureRandom);
        Key secretKey = generator.generateKey();

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            BigInteger bigInteger = new BigInteger(cipherText, 16);
            byte[] c = bigInteger.toByteArray();
            byte[] result = cipher.doFinal(c);
            String plainText = new String(result, "UTF-8");
            System.out.println(plainText);
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String md5Crypt(String ss) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        MessageDigest md= java.security.MessageDigest.getInstance("MD5");
//        return new String(md.digest(ss.getBytes("utf-8")),"utf-8");
//        byte[] gg= DigestUtils.getMd5Digest().digest(ss.getBytes("UTF-8"));
//        return new String(gg,"UTF-8");
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(ss.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        return hashtext;
    }
}
