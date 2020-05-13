import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class App {
    public static void main(String[] args) throws Exception {
        
        String secretKey=" Thuan Dep Trai";
        System.out.println("Nhập chuỗi bất kì từ bàn phím:");
        Scanner sc= new Scanner(System.in);
        String strToEncrypt= sc.nextLine();

        // mã hoa
        String encryptedString =App.encypted(strToEncrypt, secretKey);
        System.out.println(encryptedString);

        // giải mã 
        String decryptedString= App.decypted(encryptedString, secretKey);
        System.out.println(decryptedString);


    }



    //  Mã hóa
    public static String encypted(String strToEncrypt, String myKey) {
        try {
            //  tạo secretKey
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key= myKey.getBytes("UTF-8");
            key=sha.digest(key);
            key= Arrays.copyOf(key, 16);
            SecretKeySpec secretKey=  new SecretKeySpec(key, "AES");

            //  Tiến hnahf mã hóa bằng secretKey vừa tạo
            Cipher cipher= Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(cipher.ENCRYPT_MODE, secretKey);
            byte[] result=cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
             return Base64.getEncoder().encodeToString(result);
        } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    

    // Giải mã
    
    public static String decypted(String strToDecrypt, String myKey) {
        try {
            //  tạo secretKey
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key= myKey.getBytes("UTF-8");
            key=sha.digest(key);
            key= Arrays.copyOf(key, 16);
            SecretKeySpec secretKey=  new SecretKeySpec(key, "AES");

            //  Tiến hnahf giai mã bằng secretKey vừa tạo
            Cipher cipher= Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(cipher.DECRYPT_MODE, secretKey);
            byte[] result=cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
             return new String(result);
        } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
