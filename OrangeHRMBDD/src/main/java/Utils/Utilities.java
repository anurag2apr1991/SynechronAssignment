package Utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Utilities {
	
	private static String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			  + "abcdefghijklmnopqrstuvwxyz"
	          + "0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";

	public static String randomStringGenerator(int symbols) {
		return RandomStringUtils.random(symbols, characterSet);
	}
	public static int randomIntGenerator(int low, int high) {
		return (int)((Math.random()*high)+low);
	}
	
	public static String randomPasswordGenerator() {
		StringBuilder password = new StringBuilder();
		String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String SMALL_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	    String NUMBERS = "0123456789";
	    String SYMBOLS = "!@#$%^&*_=+-/";
	    password.append(getCharacterGenerator(CAPITAL_LETTERS));
        password.append(getCharacterGenerator(SMALL_LETTERS));
        password.append(getCharacterGenerator(NUMBERS));
        password.append(getCharacterGenerator(SYMBOLS));
        
        for (int i = 0; i < 4; i++) {
            password.append(getCharacterGenerator(CAPITAL_LETTERS + SMALL_LETTERS + NUMBERS + SYMBOLS));
        }
        return password.toString();
	}
	
	private static char getCharacterGenerator(String characterSet) {
        int randomIndex = new Random().nextInt(characterSet.length());
        return characterSet.charAt(randomIndex);
    }
}
