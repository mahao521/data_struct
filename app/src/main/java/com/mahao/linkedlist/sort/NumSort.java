package com.mahao.linkedlist.sort;

import android.provider.Telephony;
import android.text.InputType;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by mahao on 2018/4/10.
 */

public class NumSort {


    /**
     *   冒泡排序  外层for寻找次数   内层比较
     * @param array
     */
    public static void bubbleSort(int[] array){

        for(int i = array.length-1; i > 0; i--){

            boolean flag = true;
            for(int j = 0; j < i; j++){
                if(array[j] > array[j+1]){

                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }


    /**
     *   选择排序
     * @param array
     */
    public static void selectSort(int[] array){

        for(int i = 0; i < array.length-1; i++){

            for(int j = i; j < array.length;j++){

                if(array[i] > array[j]){

                   int temp = array[i];
                   array[i] = array[j];
                   array[j] = temp;
                }
            }
        }
    }


    /**
     *   寻找数值为des的下标
     * @param array
     * @param des
     * @return
     */
    public static int search(int[] array,int des){
        for(int i = 0; i < array.length; i++){
            if(array[i] == des){
                return i;
            }
        }
        return -1;
    }


    /**
     *   打印n到0；
     * @param n
     */
    public static void fun(int n){

        if(n < 0){
            return;
        }else{
            fun(n-1);
            System.out.print(n+" ");
        }
    }


    /**
     *   斐波那契数列
     *   1 1  2   3  5  8  13  21 34 55
     */
    public static  int fibonaqieSequence(int n){

        int result;
        if(n ==1 || n ==2 ){
            result = n;
        }else{

            result = fibonaqieSequence(n-1) + fibonaqieSequence(n-2);
        }
        return result;
    }

    /**
     *   汉诺塔
     * @param n       盘子的个数
     * @param start   开始的柱子
     * @param end     结束的柱子
     */
    public static void hanoi(int n,int start,int mid,int end){

        if(n <= 1){
            System.out.println(start + "----->" + end);
        }else{
            hanoi(n-1,start,end,mid);  //把第一个上的n-1个移到中间
            System.out.println(start + "----->" + end);
            hanoi(n-1,mid,start,end);  //把中间的N-1个移到最后
        }
    }


    public static void main(String[] args){

        //hanoi(3,1,2,3);

        //System.out.println(fibonaqieSequence(10));

        // fun(10);

       int[] arr = new int[]{3,1,5,8,2,9,4,6,7};
       //selectSort(arr);
        bubbleSort(arr);

       System.out.println(Arrays.toString(arr));
    }

}



















