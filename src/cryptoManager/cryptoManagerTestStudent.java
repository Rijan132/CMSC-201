package cryptoManager;

import static org.junit.Assert.*;
import org.junit.Test;

public class cryptoManagerTestStudent {

	@Test
	public void testIsStringInBounds() {
	    assertTrue(CryptoManager.isStringInBounds("HELLO123"));
	    assertTrue(CryptoManager.isStringInBounds("JAVA CODE"));
	    assertFalse(CryptoManager.isStringInBounds("hello"));
	}


@Test
public void testCaesarEncryption() {
    assertEquals("BCD", CryptoManager.caesarEncryption("ABC", 1));
    assertEquals("234", CryptoManager.caesarEncryption("123", 1));
}

@Test
public void testCaesarDecryption() {
    assertEquals("ABC", CryptoManager.caesarDecryption("BCD", 1));
    assertEquals("123", CryptoManager.caesarDecryption("234", 1));
}

@Test
public void testVigenereEncryption() {
    String original = "HELLO123";
    String key = "JAVA";

    String encrypted = CryptoManager.vigenereEncryption(original, key);

    assertFalse(original.equals(encrypted));
    assertEquals(original.length(), encrypted.length());
}
@Test
public void testVigenereDecryption() {
    String original = "COMPUTER123";
    String key = "JAVA";

    String encrypted = CryptoManager.vigenereEncryption(original, key);
    String decrypted = CryptoManager.vigenereDecryption(encrypted, key);

    assertEquals(original, decrypted);
}
@Test
public void testPlayfairEncryption() {
    String original = "HELLO123";
    String key = "JAVA";

    String encrypted = CryptoManager.playfairEncryption(original, key);

    assertFalse(original.equals(encrypted));
    assertEquals(original.length(), encrypted.length());
}
@Test
public void testPlayfairDecryption() {
    String original = "COMPUTER123";
    String key = "JAVA";

    String encrypted = CryptoManager.playfairEncryption(original, key);
    String decrypted = CryptoManager.playfairDecryption(encrypted, key);

    assertEquals(original, decrypted);
}
}