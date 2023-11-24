import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class AES{
  private static SecretKeySpec secretKey;
  private static byte[] key;
  public static void setKey(String myKey){
   MessageDigest sha=null;
   try{
     key=myKey.getBytes("UTF-8");
     sha=MessageDigest.getInstance("SHA-1");
     key=sha.digest(key);
     key=Arrays.copyOf(key,16);
     secretKey=new SecretKeySpec(key, "AES");
   }
   catch(NoSuchAlgorithmException e){
     e.printStackTrace();
   }catch(UnsupportedEncodingException e){
    e.printStackTrace();
   }
  }
  public static String encrypt(String strToEncrypt, String secret){
   try{
     setKey(secret);
     Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
     cipher.init(Cipher.ENCRYPT_MODE,secretKey);
     return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
   }
   catch(Exception e){
     System.out.println(e);
   }
   return null;
  }
  public static String decrypt(String strToDecrypt,String secret){
    try{
      setKey(secret);
      Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE,secretKey);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
     catch(Exception e){
        System.out.println(e);
     }
     return null;
  }
  public static void main(String[] args){
     System.out.println("Enter the Secret key: ");
     String secretKey= System.console().readLine();
     System.out.println("Enter the original URL: ");
     String originalString=System.console().readLine();
     String encryptedString= AES.encrypt(originalString,secretKey);
     String decryptedString= AES.decrypt(encryptedString,secretKey);
     System.out.println("URL Encryptingusing AES algorithm ");
     System.out.println("Original URL :"+originalString);
     System.out.println("Encrypted URL :"+encryptedString);
     System.out.println("Decrypted URL: "+decryptedString);
   }
}