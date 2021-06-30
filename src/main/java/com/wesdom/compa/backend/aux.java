package com.wesdom.compa.backend;

import com.thoughtworks.xstream.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class aux {
    static int[] myArray = {1,2,9,2,5,3,5,1,5};
    static int n=3;
    public static void main (String args[]) {
        int[][] matrix = new int[n][n];
        for(int  i = 0 ; i < myArray.length ; i++){
            matrix[(int)i/n][i%n] = myArray[i];
        }
        List<Integer> res =getMinPath(matrix);
        List<String> str = new ArrayList<>();
        for(int i = res.size() -1 ; i>= 0 ; i--){
            str.add(String.valueOf(res.get(i)));
        }
        String s = String.join(" ", str);
        System.out.println(s);
    }
    public static List<Integer> getMinPath(int[][] matrix){
        int minVal = Integer.MAX_VALUE;
        List<Integer> result = null;
        for(int i = 0 ; i < n ; i++){
            List<Integer> res = getMinPathAux(matrix,i,0);
            res = res.subList(1,res.size()  );
            int val = res.stream().reduce(0,Integer::sum);
            if(val < minVal){
                result = res;
                minVal = val;
            }
        }
        return result;
    }

    public static List<Integer> getMinPathAux(int[][] matrix, int i, int j){
        List<Integer> result = new ArrayList<>();
        if(i < 0 || j < 0 || i >= n || j >= n ){
            result.add(Integer.MAX_VALUE);
            return result;
        }
        if(j == n){
            result.add(matrix[i][j]);
            return result;
        }
        List<Integer> resA = getMinPathAux(matrix,i-1,j+1);
        List<Integer> resB = getMinPathAux(matrix,i,j+1);
        List<Integer> resC = getMinPathAux(matrix,i+1,j+1);

        int valA =  resA.stream().reduce(0, Integer::sum);
        int valB =  resB.stream().reduce(0, Integer::sum);
        int valC =  resC.stream().reduce(0, Integer::sum);

        if(valA <= valB && valA <= valC){
            resA.add(matrix[i][j]);
            result = resA;
        }else if(valB <= valA && valB <= valC){
            resB.add(matrix[i][j]);
            result = resB;
        }else if(valC <= valA && valC <= valB){
            resC.add(matrix[i][j]);
            result = resC;
        }
        return result;
    }
}
