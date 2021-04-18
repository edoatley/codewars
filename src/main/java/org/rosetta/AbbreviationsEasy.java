package org.rosetta;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AbbreviationsEasy {

    /**
     * Notes concerning the below command table:
     *
     *   - it can be thought of as one long literal string   (with blanks at end-of-lines)
     *   - it may have superfluous blanks
     *   - it may be in any case (lower/upper/mixed)
     *   - the order of the words in the *command table* must be preserved as shown
     *   - the user input(s) may be in any case (upper/lower/mixed)
     *   - commands will be restricted to the Latin alphabet   (A ──► Z,   a ──► z)
     *   - A valid abbreviation is a word that has:
     *       - at least the minimum length of the number of capital letters of the word in the command table
     *       - compares equal (regardless of case) to the leading characters of the word in the command table
     *       - a length not longer than the word in the command table
     *             - ALT,   aLt,   ALTE,   and   ALTER   are all abbreviations of   ALTer
     *             - AL,   ALF,   ALTERS,   TER,   and   A   aren't valid abbreviations of   ALTer
     *             - The number of capital letters in   ALTer   indicates that any abbreviation for   ALTer   must be at least three letters
     *             - Any word longer than five characters can't be an abbreviation for   ALTer
     *             - o,   ov,   oVe,   over,   overL,   overla   are all acceptable abbreviations for   Overlay
     *       - if there isn't any lowercase letters in the word in the command table,   then there isn't an abbreviation permitted
     */
    public static final String commandTable  =  "   Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy\n" +
                                                "   COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find\n" +
                                                "   NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput\n" +
                                                "   Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO\n" +
                                                "   MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT\n" +
                                                "   READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT\n" +
                                                "   RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus  TOP TRAnsfer Type Up";

    public static final List<String> commandTableSearchable = Arrays.stream(commandTable.replace('\n', ' ')
            .split("\\s+")).filter(s -> s.length() > 0).collect(Collectors.toList());
    public static final String ERROR = "*error*";

    /**
     *
     The command table needn't be verified/validated.
     Write a function to validate if the user "words"   (given as input)   are valid   (in the command table).
     If the word   is   valid,   then return the full uppercase version of that "word".
     If the word isn't valid,   then return the lowercase string:   *error*       (7 characters).
     A blank input   (or a null input)   should return a null string.
     Show all output here.
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
        return commandTableSearchable.stream()
                .filter(c -> tokenLength <= c.length())
                .filter(c -> tokenLength >= c.chars()
                        .mapToObj(ch -> (char) ch)
                        .filter(Character::isUpperCase)
                        .count())
                .filter(c -> c.substring(0, tokenLength).equalsIgnoreCase(token))
                .map(String::toUpperCase)
                .findFirst();
    }
}
