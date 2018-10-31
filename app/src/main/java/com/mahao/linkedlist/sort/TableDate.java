package com.mahao.linkedlist.sort;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import com.mahao.linkedlist.R;

import java.security.PublicKey;
import java.util.zip.Adler32;

/**
 * Created by mahao on 2018/4/22.
 */

public class TableDate {


    //8个人 每个人要和其他人比赛一场，每一天比赛人数不同 求赛程表
    //思想： 分治法

    /**
     *    k = 天数 2~k个选手
     * @param k
     * @return
     */
    public static int[][] table(int k){

        int n = 1 << k;
        int[][] array = new int[n][n];
        //先填好一行的内容
        for(int i = 0; i < n; i++){
            array[0][i] = i+1;
        }
        //开始使用分治法，对数据填写
        for(int i = 1; i < n; i= i * 2){  //拷贝的轮数

            for(int j = 0;  j < n; j = j + i*2){

                System.out.println("i==" + i +"j ==" +j);
                copy(array,0,j,i,j+i,i);
                copy(array,0,i+j,i,j,i);

                System.out.println(0+"-"+j+"-"+i+"-"+(j+i)+"-"+i);
                System.out.println(0+"-"+(i+j)+"-"+i+"-"+j+"-"+i);
                for(int z = 0;z < array.length; z++){
                    for(int m = 0;  m < array.length; m++){
                        System.out.print(array[z][m]+" ");
                    }
                    System.out.println();
                }

            }
        }
        return array;
    }

    /**
     *    用来拷贝数组中的一块区域
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @param  r : 表示对角线上元素的个数  要拷贝的元素的个数
     */
    public static void copy(int[][] array,int fromX , int fromY, int toX , int toY,int r){

        for (int i = 0; i < r; i++) {

            for(int j = 0; j < r; j++){

                array[toX+i][toY+j] = array[fromX+i][fromY+j];
            }
        }

    }

    public static void main(String[] args){

        int[][] arr = table(3);
        for(int i = 0;i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }


}
