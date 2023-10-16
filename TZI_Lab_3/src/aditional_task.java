import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.util.Base64;

public class aditional_task {
        public static void main(String[] args) {
            try {
                System.setOut(new PrintStream(System.out, true, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {
                BigInteger p = new BigInteger("13");
                BigInteger q = new BigInteger("83");

                BigInteger n = p.multiply(q);

                BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

                BigInteger e = findLargestPrime(p);

                BigInteger d = e.modInverse(phi);

                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(2048);
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                PrivateKey privateKey = keyPair.getPrivate();
                PublicKey publicKey = keyPair.getPublic();

                String message1 = "Г05";

                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] messageBytes = message1.getBytes("UTF-8");
                byte[] messageHash = messageDigest.digest(messageBytes);

                Signature signature = Signature.getInstance("SHA256withRSA");
                signature.initSign(privateKey);
                signature.update(messageHash);
                byte[] digitalSignature = signature.sign();

                System.out.println("Повідомлення: " + message1);
                System.out.println("Підпис: " + Base64.getEncoder().encodeToString(digitalSignature));

                signature.initVerify(publicKey);
                signature.update(messageHash);
                boolean isSignatureValid = signature.verify(digitalSignature);

                if (isSignatureValid) {
                    System.out.println("Перевірка автентичності: Підпис коректний");
                } else {
                    System.out.println("Перевірка автентичності: Підпис некоректний");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static BigInteger findLargestPrime(BigInteger n) {
            for (BigInteger i = n.subtract(BigInteger.ONE); i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
                if (isPrime(i)) {
                    return i;
                }
            }
            return BigInteger.ONE;
        }

        public static boolean isPrime(BigInteger n) {
            if (n.compareTo(BigInteger.ONE) <= 0) {
                return false;
            }
            if (n.compareTo(BigInteger.valueOf(2)) == 0) {
                return true;
            }
            if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                return false;
            }
            for (BigInteger i = BigInteger.valueOf(3); i.multiply(i).compareTo(n) <= 0; i = i.add(BigInteger.valueOf(2))) {
                if (n.mod(i).equals(BigInteger.ZERO)) {
                    return false;
                }
            }
            return true;
        }

}
