package demos.encrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DemoBlowfish {

    public  static void main(String[] ss) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Blowfish blowfish = new Blowfish("abc");
        System.out.println(blowfish.encryptString("123456"));
        String crypt1 = new Blowfish("abc").encryptString("123456");
        String crypt2 = new Blowfish("abc").encryptString("123456");
        System.out.println(crypt1);
        System.out.println(crypt2);

        System.out.println(new Blowfish("abc").decryptString(crypt1));
        System.out.println(new Blowfish("abc").decryptString(crypt2));
    }

    // obpm

    private Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String Key = "obpm";
        byte[] KeyData = Key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, KS);
        return cipher;
    }


}
