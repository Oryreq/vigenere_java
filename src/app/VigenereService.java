package app;

import utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * The VigenereService realize a Chiffre de Vigen?re.
 * @author Vsevolod Ashihmin.
 * @Date 13.12.2023.
 */
public class VigenereService {
    public record EncryptionResult(String encryptedMessage, String key) {};
    private record PreparedData(String message, String key) {};


    private final ArrayList<ArrayList<String>> VIGENERE_TABLE;

    public VigenereService(String[] alphabet) {
        this.VIGENERE_TABLE = generateVigenereTable(alphabet);
    }


    private ArrayList<ArrayList<String>> generateVigenereTable(String[] alphabet) {
        var shiftedAlphabet = alphabet.clone();
        ArrayList<ArrayList<String>> table = new ArrayList<>();
        table.add(new ArrayList<>());
        table.get(0).add("-");
        Arrays.stream(alphabet).forEach(letter -> table.get(0).add(letter));

        Arrays.stream(alphabet).forEach(letter -> {
            ArrayList<String> line = new ArrayList<>();
            line.add(letter);
            line.addAll(Arrays.asList(shiftedAlphabet));
            ArrayUtils.moveArrayLeft(shiftedAlphabet, 1);
            table.add(line);
        });

        //String[][] matrix = new String[table.size()][table.get(0).size()];
        //IntStream.range(0, table.size()).forEach(i -> matrix[i] = table.get(i).toArray(String[]::new));
        return table;
    }

    /**
     * Encrypt the message by Chiffre de Vigen?re.
     * @param message non-encrypted message.
     * @param key encryption key.
     * @return decrypted message.
     */
    public EncryptionResult encrypt(String message, String key) {
        var data = prepareData(message, key);

        IntFunction<String> encryptLetters = (i) -> this.encryptLetter(data.message.charAt(i) + "", data.key.charAt(i) + "");

        var encryptedMessage = IntStream.range(0, data.key.length())
                                                .mapToObj(encryptLetters)
                                                .reduce((text, string) -> text += string)
                                                .get();

        return new EncryptionResult(encryptedMessage, key);
    }

    /**
     * Decrypt the message by Chiffre de Vigen?re.
     * @param encryptedMessage encrypted message.
     * @param key encryption key.
     */
    public String decrypt(String encryptedMessage, String key) {
        String finalKey = prolongKey(key, encryptedMessage);
        IntFunction<String> decryptLetters = (i) -> this.decryptLetter(encryptedMessage.charAt(i) + "", finalKey.charAt(i) + "");

        return IntStream.range(0, encryptedMessage.length())
                        .mapToObj(decryptLetters)
                        .reduce((text, string) -> text += string)
                        .get();
    }

    /**
     * Back substitutes two letters and find the decrypted letter.
     * @param encryptLetter
     * @param keyLetter
     * @return decrypted letter.
     */
    public String decryptLetter(String encryptLetter, String keyLetter) {
        var i = VIGENERE_TABLE.get(0).indexOf(keyLetter);

        var tmp = ArrayUtils.slice(VIGENERE_TABLE.get(i), 1);
        var j = tmp.indexOf(encryptLetter) + 1;

        return VIGENERE_TABLE.get(0).get(j);
    }

    /**
     * Removes all unnecessary symbols from message and transform it to upper case.
     * Prolongs key by length message.
     * @param message message from user.
     * @param key key from user.
     * @return message without unecessary symbols and prolonged key.
     */
    private PreparedData prepareData(String message, String key) {
        message = message.replaceAll(" ", "")
                        .replaceAll("\\.", "")
                        .replaceAll(",", "")
                        .replaceAll("-", "")
                        .replaceAll("\n", "")
                        .replaceAll(":", "")
                        .replaceAll("—", "")
                        .replaceAll("¹","")
                        .replaceAll("0", "")
                        .replaceAll("1", "")
                        .replaceAll("2", "")
                        .replaceAll("3", "")
                        .replaceAll("4", "")
                        .replaceAll("5", "")
                        .replaceAll("6", "")
                        .replaceAll("7", "")
                        .replaceAll("8", "")
                        .replaceAll("9", "")
                        .replaceAll("–", "")
                        .toUpperCase();

        return new PreparedData(message, prolongKey(key, message));
    }

    /**
     * Prolong key by message length.
     * @param key short key.
     * @param message message.
     * @return long key.
     */
    private String prolongKey(String key, String message) {
        key = key.toUpperCase();
        StringBuilder newString = new StringBuilder();
        for (int i = 0, j = 0; i < message.length(); i++, j++) {
            if (j >= key.length()) {
                j = 0;
            }
            newString.append(key.charAt(j));
        }
        return newString.toString();
    }

    /**
     * Returns letter by intersection of two letters in Table de Vigen?re.
     * @param messageLetter letter from message
     * @param keyLetter letter from key
     * @return letter
     */
    private String encryptLetter(String messageLetter, String keyLetter) {
        var i = VIGENERE_TABLE.get(0).indexOf(messageLetter);
        var j = VIGENERE_TABLE.get(0).indexOf(keyLetter);
        return VIGENERE_TABLE.get(i).get(j);
    }

}
