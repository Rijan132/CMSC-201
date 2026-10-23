/**
 * This is a utility class that encrypts and decrypts a phrase using three
 * different approaches. 
 * 
 * The first approach is called the Vigenere Cipher.Vigenere encryption 
 * is a method of encrypting alphabetic text based on the letters of a keyword.
 * 
 * The second approach is Playfair Cipher. It encrypts two letters (a digraph) 
 * at a time instead of just one.
 * 
 * The third approach is Caesar Cipher. It is a simple replacement cypher. 
 * 
 * @author Huseyin Aygun
 * @version 8/3/2025
 */
package cryptoManager;
public class CryptoManager { 

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
    // Use 64-character matrix (8X8) for Playfair cipher  
    private static final String ALPHABET64 = " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_";

    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }
        return true;
    }

	/**
	 * Vigenere Cipher is a method of encrypting alphabetic text 
	 * based on the letters of a keyword. It works as below:
	 * 		Choose a keyword (e.g., KEY).
	 * 		Repeat the keyword to match the length of the plaintext.
	 * 		Each letter in the plaintext is shifted by the position of the 
	 * 		corresponding letter in the keyword (A = 0, B = 1, ..., Z = 25).
	 */   

    public static String vigenereEncryption(String plainText, String key) {
    	if (!isStringInBounds(plainText)) {
            return "The selected string is not in bounds, Try again.";
        }

        String encrypted = "";

        for (int i = 0; i < plainText.length(); i++) {

            char ch = plainText.charAt(i);

            char keyChar = key.charAt(i % key.length());

            int shift = keyChar - LOWER_RANGE;

            int shifted = (ch - LOWER_RANGE + shift) % RANGE;

            encrypted += (char)(shifted + LOWER_RANGE);
        }

        return encrypted;
    }

    // Vigenere Decryption
    public static String vigenereDecryption(String encryptedText, String key) {

        String decrypted = "";

        for (int i = 0; i < encryptedText.length(); i++) {

            char ch = encryptedText.charAt(i);

            char keyChar = key.charAt(i % key.length());

            int shift = keyChar - LOWER_RANGE;

            int shifted = (ch - LOWER_RANGE - shift) % RANGE;

            if (shifted < 0) {
                shifted += RANGE;
            }

            decrypted += (char)(shifted + LOWER_RANGE);
        }

        return decrypted;
    }


	/**
	 * Playfair Cipher encrypts two letters at a time instead of just one.
	 * It works as follows:
	 * A matrix (8X8 in our case) is built using a keyword
	 * Plaintext is split into letter pairs (e.g., ME ET YO UR).
	 * Encryption rules depend on the positions of the letters in the matrix:
	 *     Same row: replace each letter with the one to its right.
	 *     Same column: replace each with the one below.
	 *     Rectangle: replace each letter with the one in its own row but in the column of the other letter in the pair.
	 */    

    public static String playfairEncryption(String plainText, String key) {
        if (!isStringInBounds(plainText)) {
            return "The selected string is not in bounds, Try again.";
        }
        char[][] matrix = createPlayfairMatrix(key);

        String encrypted = "";

        for (int i = 0; i < plainText.length(); i += 2) {
            char first = plainText.charAt(i);
            if (i + 1 >= plainText.length()) {
                encrypted += first;
                break;
            }
            char second = plainText.charAt(i + 1);
          
            int row1 = 0, col1 = 0;
            int row2 = 0, col2 = 0;

            // Find both characters in the matrix
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (matrix[row][col] == first) {
                        row1 = row;
                        col1 = col;
                    }
                    if (matrix[row][col] == second) {
                        row2 = row;
                        col2 = col;
                    }
                }
            }

            if (row1 == row2) {
                encrypted += matrix[row1][(col1 + 1) % 8];
                encrypted += matrix[row2][(col2 + 1) % 8];

            } else if (col1 == col2) {
                // Same column: move down
                encrypted += matrix[(row1 + 1) % 8][col1];
                encrypted += matrix[(row2 + 1) % 8][col2];

            } else {
                // Rectangle: swap columns
                encrypted += matrix[row1][col2];
                encrypted += matrix[row2][col1];
            }
        }

        return encrypted;
    }

    // Vigenere Decryption
    public static String playfairDecryption(String encryptedText, String key) {
    	 char[][] matrix = createPlayfairMatrix(key);

    	    String decrypted = "";

    	    for (int i = 0; i < encryptedText.length(); i += 2) {
    	        char first = encryptedText.charAt(i);
    	        if (i + 1 >= encryptedText.length()) {
    	            decrypted += first;
    	            break;
    	        }
    	        char second = encryptedText.charAt(i + 1);

    	        int row1 = 0, col1 = 0;
    	        int row2 = 0, col2 = 0;

    	        // Find both characters in the matrix
    	        for (int row = 0; row < 8; row++) {
    	            for (int col = 0; col < 8; col++) {

    	                if (matrix[row][col] == first) {
    	                    row1 = row;
    	                    col1 = col;
    	                }

    	                if (matrix[row][col] == second) {
    	                    row2 = row;
    	                    col2 = col;
    	                }
    	            }
    	        }

    	        if (row1 == row2) {
    	            // Same row: move left
    	            decrypted += matrix[row1][(col1 + 7) % 8];
    	            decrypted += matrix[row2][(col2 + 7) % 8];

    	        } else if (col1 == col2) {
    	            // Same column: move up
    	            decrypted += matrix[(row1 + 7) % 8][col1];
    	            decrypted += matrix[(row2 + 7) % 8][col2];

    	        } else {
    	            // Rectangle: swap columns
    	            decrypted += matrix[row1][col2];
    	            decrypted += matrix[row2][col1];
    	        }
    	    }

    	    return decrypted;
    }
    /**
     * Caesar Cipher is a simple substitution cipher that replaces each letter in a message 
     * with a letter some fixed number of positions down the alphabet. 
     * For example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and so on.
     */    
 
    public static String caesarEncryption(String plainText, int key) {
    	if (!isStringInBounds(plainText)) {
            return "The selected string is not in bounds, Try again.";
        }

        String encrypted = "";

        for (int i = 0; i < plainText.length(); i++) {
            char ch = plainText.charAt(i);
            int shifted = (ch - LOWER_RANGE + key) % RANGE;
            if (shifted < 0) {
                shifted += RANGE;
            }
            encrypted += (char)(shifted + LOWER_RANGE);
        }
        return encrypted;
    }

    // Caesar Decryption
    public static String caesarDecryption(String encryptedText, int key) {
    	  String decrypted = "";

    	    for (int i = 0; i < encryptedText.length(); i++) {
    	        char ch = encryptedText.charAt(i);
    	        int shifted = (ch - LOWER_RANGE - key) % RANGE;
    	        if (shifted < 0) {
    	            shifted += RANGE;
    	        }
    	        decrypted += (char)(shifted + LOWER_RANGE);
    	    }
    	    return decrypted;
    }    


private static char[][] createPlayfairMatrix(String key) {

    char[][] matrix = new char[8][8];

    String characters = "";

    // Add unique characters from the key
    for (int i = 0; i < key.length(); i++) {
        char ch = key.charAt(i);
        if (ALPHABET64.indexOf(ch) != -1 &&
                characters.indexOf(ch) == -1) {
            characters += ch;
        }
    }

    // Add the remaining alphabet characters
    for (int i = 0; i < ALPHABET64.length(); i++) {
        char ch = ALPHABET64.charAt(i);
        if (characters.indexOf(ch) == -1) {
            characters += ch;
        }
    }
    // Fill the 8x8 matrix
    int index = 0;

    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            matrix[row][col] = characters.charAt(index);
            index++;
        }
    }

    return matrix;
}
}