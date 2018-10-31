package com.mahao.linkedlist.LongComSub;

import java.util.Arrays;

/**
 * Created by mahao on 2018/5/6.
 */

public class BaHuangHou {

    //array[n] 表示第n列  n表示第n行
    public static int[] array = new int[8];


    /**
     *  八皇后问题，递归调用----类似于动态规划
     * @param row
     */
    public static void enghtQueens(int row){

        //如果有结果直接退出
        if(row == 8){
          printResult();
          return;
        }
        for(int i = 0; i < 8; i++){ //控制每一层循环的个数。---当递归回溯时，for循环继续向后循环。

            array[row] = i;  //记录每一个皇后的位置
            if(judge(row)){
                enghtQueens(row+1);
            }
        }
    }

    /**
     *  可以判定当前列放入的位置是否和以前放过的内容有冲突
     * @param n
     * @return
     */
    public static boolean judge(int n){
        for(int i = 0 ; i < n ; i++){
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){  //斜率问题
                return false;
            }
        }
        return true;
    }

    public static void printResult(){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }


    /**
     *  8皇后有64种结果
     * @param args
     */
    public static void main(String[] args){

        enghtQueens(0);
    }
}
