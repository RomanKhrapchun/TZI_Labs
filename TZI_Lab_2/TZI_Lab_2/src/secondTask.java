import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class secondTask {

    public static String generateVigenereKey(String key, int length) {
        key = key.toUpperCase();
        int keyLength = key.length();
        StringBuilder fullKey = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char keyChar = key.charAt(i % keyLength);
            fullKey.append(keyChar);
        }

        return fullKey.toString();
    }

    public static String vigenereEncrypt(String plainText, String key) {
        StringBuilder encryptedText = new StringBuilder();
        key = generateVigenereKey(key, plainText.length());

        for (int i = 0; i < plainText.length(); i++) {
            char charToEncrypt = plainText.charAt(i);

            if (Character.isLetter(charToEncrypt)) {
                char base = Character.isUpperCase(charToEncrypt) ? 'А' : 'а';
                int shift = key.charAt(i) - base;
                char encryptedChar = (char) ((((charToEncrypt - base + shift) + 32) % 32) + base);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(charToEncrypt);
            }
        }

        return encryptedText.toString();
    }

    public static String vigenereDecrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        key = generateVigenereKey(key, encryptedText.length());

        for (int i = 0; i < encryptedText.length(); i++) {
            char charToDecrypt = encryptedText.charAt(i);

            if (Character.isLetter(charToDecrypt)) {
                char base = Character.isUpperCase(charToDecrypt) ? 'А' : 'а';
                int shift = key.charAt(i) - base;
                char decryptedChar = (char) ((((charToDecrypt - base - shift + 32) % 32) + base));
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(charToDecrypt);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String textToEncrypt = "Ваш текст для шифрування тут. Може бути довше за 80 слів.";
        String encryptionKey = "КЛЮЧІК";

        // Шифрування тексту
        String encryptedText = vigenereEncrypt(textToEncrypt, encryptionKey);
        System.out.println("Зашифрований текст:");
        System.out.println(encryptedText);

        // Розшифрування тексту
        String decryptedText = vigenereDecrypt(encryptedText, encryptionKey);
        System.out.println("\nРозшифрований текст:");
        System.out.println(decryptedText);
    }
}
