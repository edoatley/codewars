package org.codewars.morse;

import java.util.HashMap;

public class MorseCode {
    private static final Character[] english = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            ',', '.', '?' };

    private static final String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "--..--", ".-.-.-", "..--.." };

    private static final HashMap<String, String> morseToString = new HashMap<>();

    static {
        for (int i = 0; i < english.length; i++) {
            morseToString.put(morse[i], english[i].toString().toUpperCase());
        }
    }

    public static String get(String morse) {
        return morseToString.get(morse);
    }
}
