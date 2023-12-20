package org.hacker;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Hourglass {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }
        int max = maxHourGlass(arr);
        System.out.println(max);
        scanner.close();
    }
/*
1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 2 4 4 0
0 0 0 2 0 0
0 0 1 2 4 0
 */

    public static int maxHourGlass(int[][] arr) {
        int max = Integer.MIN_VALUE;
        for (int y = 0; y < arr.length - 2; y++) {
            for (int x = 0; x < arr.length - 2; x++) {
                int hourglass = calculate(arr, x, y);
                if (hourglass > max) {
                    max = hourglass;
                }
            }
        }
        return max;
    }

    private static int calculate(int[][] arr, int row, int col) {
        return arr[row][col]
             + arr[row][col+1]
             + arr[row][col+2]
             + arr[row+1][col+1]
             + arr[row+2][col]
             + arr[row+2][col+1]
             + arr[row+2][col+2];
    }
}
