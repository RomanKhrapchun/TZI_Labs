import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Task_1 {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Завдання 1.1
        BigInteger p = new BigInteger("13");
        BigInteger q = new BigInteger("83");

        BigInteger n = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = findLargestPrime(p);

        BigInteger d = e.modInverse(phi);

        System.out.println("Відкритий ключ (n, e): (" + n + ", " + e + ")");
        System.out.println("Закритий ключ (n, d): (" + n + ", " + d + ")");

        //Завдання 1.2
        String message = "ТІНДА2";

        BigInteger encryptedMessage = encrypt(message, n, e);
        System.out.println("Зашифроване повідомлення: " + encryptedMessage);
    }

    public static BigInteger encrypt(String message, BigInteger n, BigInteger e) {
        byte[] bytes = message.getBytes();
        BigInteger m = new BigInteger(bytes);
        return m.modPow(e, n);
    }

    // Функція для знаходження найбільшого простого числа, яке менше за задане число
    public static BigInteger findLargestPrime(BigInteger n) {
        for (BigInteger i = n.subtract(BigInteger.ONE); i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
            if (isPrime(i)) {
                return i;
            }
        }
        return BigInteger.ONE;
    }

    // Функція для перевірки чи число є простим
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
