import java.util.*;
public class vignerecipher
{
    public static String encrypt(String text,final String key){
        String res="";
        text=text.toUpperCase();
        for(int i=0,j=0; i< text.length(); i++){
            char c=text.charAt(i);
            if(c<'A'||c>'z')
                continue;
            res+=(char)((c+key.charAt(j)-2*'A')%26+'A');
            j=(++j)%key.length();
        }
        return res;
    }
    public static String decrypt(String text,final String key){
        String res="";
        text=text.toUpperCase();
        for(int i=0,j=0;i<text.length();i++)
        {
            char c=text.charAt(i);
            if(c<'A'||c>'z')
               continue;
            res+=(char)((c-key.charAt(j)+26)%26+'A');
            j=(++j)%key.length();
        }
        return res;
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the key: ");
        String key = sc.nextLine();
        System.out.println("Enter the message for encrytption: ");
        String message = sc.nextLine();
        String encryptedMsg=encrypt(message,key);
        System.out.println("Encrypted message:Cipher Text=" +encryptedMsg);
        System.out.println("Decrypted message:Plain Text="+decrypt(encryptedMsg,key));
}
}