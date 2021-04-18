package org.hacker;

import java.util.Scanner;
public class BinaryNumbers {



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        String binary = Integer.toBinaryString(n);
        int max = 0;
        int curr = 0;
        for (int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '1') {
                curr++;
            } else if (curr > max){
                max = curr;
                curr = 0;
            } else {
                curr = 0;
            }
        }
        if(curr > max) max = curr;
        System.out.println(max);
        scanner.close();
    }
}

