package org.codewars.strip;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StripComments {

    public static String stripComments(String text, String[] commentSymbols) {
        return Arrays.stream(text.split("\\n"))
                .map(s -> {
                    String x = s;
                    for (String commentSymbol : commentSymbols) {
                        if(s.contains(commentSymbol)) {
                            x = s.substring(0, s.indexOf(commentSymbol));
                        }
                    }
                    return x.stripTrailing();
                })
                .collect(Collectors.joining("\n"));
    }

}
