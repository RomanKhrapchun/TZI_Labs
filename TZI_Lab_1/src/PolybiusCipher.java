public class PolybiusCipher {
        private static final char[][] POLYBIUS_TABLE = {
                {'A', 'B', 'C', 'D', 'E'},
                {'F', 'G', 'H', 'I', 'K'},
                {'L', 'M', 'N', 'O', 'P'},
                {'Q', 'R', 'S', 'T', 'U'},
                {'V', 'W', 'X', 'Y', 'Z'}
        };

        public static String encrypt(String plaintext) {
            plaintext = plaintext.toUpperCase();
            StringBuilder ciphertext = new StringBuilder();

            for (char c : plaintext.toCharArray()) {
                if (c == 'J') {
                    c = 'I';
                }

                if (Character.isLetter(c)) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (POLYBIUS_TABLE[i][j] == c) {
                                ciphertext.append(i + 1).append(j + 1).append(' ');
                            }
                        }
                    }
                } else {
                    ciphertext.append(c);
                }
            }

            return ciphertext.toString().trim();
        }

        public static String decrypt(String ciphertext) {
            StringBuilder plaintext = new StringBuilder();

            String[] parts = ciphertext.split(" ");
            for (String part : parts) {
                if (!part.isEmpty()) {
                    int row = Character.getNumericValue(part.charAt(0)) - 1;
                    int col = Character.getNumericValue(part.charAt(1)) - 1;

                    if (row >= 0 && row < 5 && col >= 0 && col < 5) {
                        plaintext.append(POLYBIUS_TABLE[row][col]);
                    }
                } else {
                    plaintext.append(' ');
                }
            }

            return plaintext.toString();
        }

        public static void main(String[] args) {
            String plaintext = "HELLO WORLD";
            String encryptedText = encrypt(plaintext);
            String decryptedText = decrypt(encryptedText);

            System.out.println("Plaintext: " + plaintext);
            System.out.println("Encrypted: " + encryptedText);
            System.out.println("Decrypted: " + decryptedText);
        }
    }
