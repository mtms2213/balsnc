import java.security.*;
import java.util.Scanner;

public class digi {
     public static KeyPair generateRSAKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }
    public static byte[] generateSignature(byte[] messageBytes, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(messageBytes);
        return signature.sign();
    }

    public static boolean isVerifiedSignature(byte[] messageBytes, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(messageBytes);
        return signature.verify(signatureBytes);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigital Signature Example\n");

        // Step 1: Input a message
        System.out.println("Enter the message:");
        String message = sc.nextLine();


        try {
            // Step 2: Generate an RSA key pair for the sender
            KeyPair keyPair = generateRSAKeyPair();

            // Step 3: Generate a digital signature for the message using the sender's private key
            byte[] signature = generateSignature(message.getBytes(), keyPair.getPrivate());

            System.out.println("\nSignature Generated:");
            for (byte b : signature) {
                String hex = String.format("%02x", b);
                System.out.print(hex);
            }
            System.out.println("\n");

            // Step 4: Input a message for the receiver
            System.out.println("Enter the message for the receiver:");
            String messageToVerify = sc.nextLine();

            // Step 5: Verify the signature using the sender's public key
            boolean signatureVerified = isVerifiedSignature(messageToVerify.getBytes(), signature, keyPair.getPublic());

            if (signatureVerified) {
                System.out.println("Signature is verified. The message is authentic.");
            } else {
                System.out.println("Signature is not verified. The message may have been tampered with.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
}
