package org.rosetta;

import java.util.*;
import java.util.stream.Collectors;

public class AbbreviationsSimple {

    /**
     * Notes concerning the following command table:
     *
     *   - it can be thought of as one long literal string   (with blanks at end-of-lines)
     *   it may have superfluous blanks
     *   it may be in any case (lower/upper/mixed)
     *   the order of the words in the   command table   must be preserved as shown
     *   the user input(s) may be in any case (upper/lower/mixed)
     *   commands will be restricted to the Latin alphabet   (A ──► Z,   a ──► z)
     *   a command is followed by an optional number, which indicates the minimum abbreviation
     *   - A valid abbreviation is a word that has:
     *     - at least the minimum length of the word's minimum number in the command table
     *     - compares equal (regardless of case) to the leading characters of the word in the command table
     *     - a length not longer than the word in the command table
     *           - ALT,   aLt,   ALTE,   and   ALTER   are all abbreviations of   ALTER 3
     *           - AL,   ALF,   ALTERS,   TER,   and   A   aren't valid abbreviations of   ALTER 3
     *           - The   3   indicates that any abbreviation for   ALTER   must be at least three characters
     *           - Any word longer than five characters can't be an abbreviation for   ALTER
     *           - o,   ov,   oVe,   over,   overL,   overla   are all acceptable abbreviations for   overlay 1
     *     - if there isn't a number after the command,   then there isn't an abbreviation permitted
     */
    public static final String commandTable  =  "   add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3\n" +
                                                "   compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate\n" +
                                                "   3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2\n" +
                                                "   forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load\n" +
                                                "   locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2\n" +
                                                "   msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3\n" +
                                                "   refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left\n" +
                                                "   2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1";

    public static final Map<String, Integer> commandTableSearchable;
    public static final String ERROR = "*error*";

    static {
        List<String> collectionOfCommandTableDetails = Arrays.stream(commandTable.replace('\n', ' ')
                .split("\\s+")).filter(s -> s.length() > 0).collect(Collectors.toList());

        commandTableSearchable = new HashMap<>();
        int size = collectionOfCommandTableDetails.size();
        for (int i = 0; i < size; i++) {
            String s = collectionOfCommandTableDetails.get(i);
            if (i+1 < size) {
                String next = collectionOfCommandTableDetails.get(i + 1);
                if(!Character.isAlphabetic(next.charAt(0))) {
                    commandTableSearchable.put(s, Integer.parseInt(next));
                    i++;
                    continue;
                }
            }
            commandTableSearchable.put(s, s.length());
        }
    }
    /**
     *   The command table needn't be verified/validated.
     *   Write a function to validate if the user "words"   (given as input)   are valid   (in the command table).
     *   If the word   is   valid,   then return the full uppercase version of that "word".
     *   If the word isn't valid,   then return the lowercase string:   *error*       (7 characters).
     *   A blank input   (or a null input)   should return a null string.
     *   Show all output here.
     */
    public String evaluate(String userInput) {
        String[] tokens = userInput.split("\\s+");
        String[] result = new String[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            Optional<String> x = lookupCommand(tokens[i]);
            result[i] = x.orElse(ERROR);
        }
        return String.join(" ", result);
    }

    private Optional<String> lookupCommand(String token) {
        final int tokenLength = token.length();
        return commandTableSearchable.entrySet().stream()
                .filter(e -> tokenLength >= e.getValue())
                .filter(e -> tokenLength <= e.getKey().length())
                .filter(e -> e.getKey().substring(0, tokenLength).equalsIgnoreCase(token))
                .map(Map.Entry::getKey)
                .map(String::toUpperCase)
                .findFirst();
    }
}
