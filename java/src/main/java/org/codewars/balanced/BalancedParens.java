package org.codewars.balanced;

import java.util.*;
import java.util.stream.Collectors;

public class BalancedParens {

    private static final class ParenString {
        String str;
        int balance;
        int openLeft;
        int closeLeft;

        public ParenString(String str, int balance, int openLeft, int closeLeft) {
            this.balance = balance;
            this.str = str;
            this.openLeft = openLeft;
            this.closeLeft = closeLeft;
        }

        public void addOpening() {
            str = str + "(";
            balance++;
            openLeft--;
        }
        public void addClosing() {
            str = str + ")";
            balance--;
            closeLeft--;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ParenString that = (ParenString) o;
            return Objects.equals(str, that.str);
        }

        @Override
        public int hashCode() {
            return Objects.hash(str);
        }
    }

    public static List<String> balancedParens(int n) {
        List<String> permutations = new ArrayList<>();
        if (n == 0) {
            permutations.add("");
            return permutations;
        }
        int charsSoFar = 1;
        int charsRequired = 2 * n;
        List<ParenString> potentialString = new ArrayList<>();
        potentialString.add(new ParenString("(", 1, n-1, n));
        while(charsRequired > charsSoFar) {
            int size = potentialString.size();
            for (int i = 0; i < size; i++) {
                ParenString s = potentialString.get(i);
                if(s.balance == 0 || s.closeLeft == 0) {
                    s.addOpening();
                }
                else if(s.balance >= s.closeLeft || s.openLeft == 0) {
                    s.addClosing();
                }
                else {
                    ParenString alt = new ParenString(s.str + "(", s.balance + 1, s.openLeft - 1, s.closeLeft);
                    potentialString.add(alt);
                    s.addClosing();
                }
            }
            charsSoFar++;
        }
        return potentialString.stream().map(p -> p.str).collect(Collectors.toList());
    }
}
