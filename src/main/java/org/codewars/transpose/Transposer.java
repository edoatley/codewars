package org.codewars.transpose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Transposer {

    public List<List<String>> transpose(List<List<String>> input) {
        return transposeMethod4(input);
    }

    private List<List<String>> transposeMethod4(List<List<String>> input) {
        final int maxRows = input.stream().mapToInt(List::size).max().orElse(-1);
        final int maxCols = input.size();
        String[][] result = new String[maxRows][maxCols];

        for (int rowIdx = 0; rowIdx < maxRows; rowIdx++) {
            for (int colIdx = 0; colIdx < maxCols; colIdx++) {
                result[rowIdx][colIdx] = input.get(colIdx).get(rowIdx);
            }
        }

        return Arrays.stream(result)
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }
    private List<List<String>> transposeMethod3(List<List<String>> input) {
        List<List<String>> result = new ArrayList<>();
        final int maxRows = input.stream().mapToInt(List::size).max().orElse(-1);
        final int maxCols = input.size();

        System.err.println("Input collection has a size " + maxCols + " and a max list size " + maxRows);

        // create a rectangular array to target
        for (int i = 0; i < maxRows; i++) {
            List<String> newList = new ArrayList<>();
            result.add(newList);
            for (int j = 0; j < input.size(); j++) {
                newList.add("");
            }
        }


//        System.err.println("Output collection has a size " + maxRows + " and a max list size " + input.size());
        // simple loop through
        for (int rowIdx = 0; rowIdx < maxRows; rowIdx++) {
            for (int colIdx = 0; colIdx < maxCols; colIdx++) {
//                System.err.println("Index["+rowIdx+"]["+colIdx+"] - " + input.get(colIdx).get(rowIdx));
                result.get(rowIdx).set(colIdx, input.get(colIdx).get(rowIdx));
            }
        }
        return result;
    }

    // only works if the input child lists are the same so you would need a pass to make them identical lengths
    private List<List<String>> transposeMethod2(List<List<String>> input) {
        int m = input.size();
        final int n = input.stream().mapToInt(List::size).max().orElse(-1);

        List<List<String>> transposedMatrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> newList = new ArrayList<>();
            transposedMatrix.add(newList);
            for (int j = 0; j < m; j++) {
                newList.add("");
            }
        }

        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                transposedMatrix.get(x).set(y, input.get(y).get(x));
            }
        }
        return transposedMatrix;
    }


    private List<List<String>> transposeMethod1(List<List<String>> input) {
        List<List<String>> result = new ArrayList<>();
        final int maxRows = input.stream().mapToInt(List::size).max().orElse(-1);
        for (int i = 0; i < maxRows; i++) {
            result.add(new ArrayList<>());
        }

        for (List<String> column: input) {
            int index = 0;
            for (String cell : column) {
                System.err.println(cell);
                result.get(index).add(cell);
                index ++;
            }
            while (maxRows > index) {
                result.get(index).add("");
                index ++;
            }
            System.err.println("End of column - index is " + index);
        }
        return result;
    }
}
