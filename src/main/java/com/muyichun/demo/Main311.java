package com.muyichun.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

/*
 * @Author muyichun
 * @Date 2022-02-16 11:55:27$
 */
public class Main311 {
    public static void main(String[] args)  {
        int[][]mat1 = {{1,0,0},{-1,0,3}};
        int[][]mat2 = {{7,0,0},{0,0,0},{0,0,1}};
        int[][]results = new Main311().multiply(mat1,mat2);
        Arrays.stream(results).forEach(e1 -> {
            Arrays.stream(e1).forEach(System.out::print);
            System.out.println();
        });
    }
//2*3  3*4
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int results[][] = new int[mat1.length][mat2[0].length];
        for(int i = 0; i < mat1.length; i++){
            for (int j = 0; j < mat2[0].length; j++){
                //计算
                for (int q = 0; q < mat2.length; q++){
                    results[i][j] += mat1[i][q] * mat2[q][j];
                }
            }
        }
        return results;
    }
}