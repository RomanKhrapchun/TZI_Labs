import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Lab_2 {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String message = "БІРОБІДЖАН_100_Т";
        String gamma = "БУДИСЛАВ";

        String encryptedMessage = encryptMessage(message, gamma);
        System.out.println("Зашифроване повідомлення: " + encryptedMessage);

        String decryptedMessage = decryptMessage(encryptedMessage, gamma);
        System.out.println("Розшифроване повідомлення: " + decryptedMessage);
    }

    public static String encryptMessage(String message, String gamma) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i);
            char gammaChar = gamma.charAt(i % gamma.length());

            // Використовуємо XOR для шифрування
            char encryptedChar = (char)(messageChar ^ gammaChar);
            encryptedMessage.append(encryptedChar);
        }

        return encryptedMessage.toString();
    }

    public static String decryptMessage(String encryptedMessage, String gamma) {
        // Декодування аналогічно шифруванню
        return encryptMessage(encryptedMessage, gamma);
    }
}
