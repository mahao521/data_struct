package com.mahao.linkedlist.LongComSub;

import java.util.Stack;

/**
 * Created by mahao on 2018/4/22.
 */

public class Lcs {

    /**        A B C D E F
     *         0 0 0 0 0 0
     *      A 01 0 0 0 0 0
     *      B 01 2 0 0 0 0
     *  s2  D 01 2 0 1 0 0
     *      E 0
     *      F 0
     *      G 0
     * @param x
     * @param y
     */
    public static void getLcs(String x,String y){

        char[] s1 = x.toCharArray();
        char[] s2 = y.toCharArray();
        int[][] array = new int[x.length()+1][y.length()+1];
        //先把第一行和第一列填上0
        for(int i = 0; i < array[0].length; i++){
            array[0][i] = 0;
        }
        for(int i = 0; i < array.length; i++){
            array[i][0] = 0;
        }
        //对数组填入数据
        //array.length ---> 数组的行数
        //array[i].length ---> i行元素的个数
        for(int i = 1; i < array.length;i++){
            for(int j = 1; j < array[i].length; j++){
                if(s1[i-1] == s2[j-1]){
                    //相同 ： 该位置左上角数值 + 1；
                   array[i][j] = array[i-1][j-1]+1;
                }else{
                    //不同： 该数目右边和上边的最大值；
                    array[i][j] = max(array[i-1][j],array[i][j-1]);
                }
            }
        }
        for(int i = 0; i < array.length;i++){
            for(int j = 0; j < array[i].length;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }


        //输出表中的最长公共子序列
        Stack result = new Stack();
        int i = x.length()-1;
        int j = y.length()-1;
        while (i >= 0 && j >= 0){
            if(s1[i] == s2[j]){
                result.push(s1[i]);
                i--;
                j--;
            }else if(array[i+1][j-1+1] > array[i-1+1][j+1]){ //数组下标0被占用
                j--;
            }else {
                i--;
            }
        }
        while(!result.isEmpty()){
            System.out.println(result.pop()+" ");
        }
    }

    public static int  max(int a,int b){
        return a > b ? a : b;
    }


    public static void main(String[] args){

        //求最长公共子序列。
       getLcs("ABCBDAB","BDCABA");
    }
}































