package com.mahao.linkedlist.LongComSub;

/**
 * Created by mahao on 2018/5/7.
 */

public class JiuGongGe {

    public static int n = 5;
    public static int[][] array = new int[n][n];


    /**
     *  九宫格规律：  1 把1放到第一行的中间
     *              2 开始向右上角放入后面的数字，如果右上角是空的，直接填入，如果右上角已经填过了，就至鸡肉填在当前位置的下面。
     * @param array 必须是其数个
     */
    public static void squaredUp(int[][] array){

        int x = 1; //需要填入的值
        int row = 0, col = n/2; //定义初始行列的位置
        array[row][col] = x;

        //按规律填写所有的数字
        while(x < n*n){

            //先记录下原来的位置
            int tempRow = row;
            int tempCol = col;
            //向右上走
            row--;
            if(row<0){
                row = n-1;
            }
            col++;
            if(col == n){
                col = 0;
            }
            x++;
            if(array[row][col] == 0){ //如果右上角没有被填过
                array[row][col] = x;
            }else{ //回溯到该节点的下一个节点
                row = tempRow;
                col = tempCol;
                row++;
                array[row][col] = x;
            }
        }
    }

    public static void main(String[] args){

        squaredUp(array);
        for(int i = 0; i < array.length;i++){
            for(int j = 0;j< array.length;j++){
              System.out.print(array[i][j] +" ");
            }
            System.out.println();
        }
    }
}
