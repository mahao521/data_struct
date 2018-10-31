package com.mahao.linkedlist.LongComSub;

import com.mahao.linkedlist.sort.JiShuSort;

import java.util.Arrays;

/**
 * Created by mahao on 2018/5/5.
 */

public class KMP {

     public static void main(String[] args){

         String sttr = "ababcabcbababcabacaba";
         String dest = "ababcaba";
         int[] array = kmpNext(dest);
         System.out.println(Arrays.toString(array));
         System.out.println(kmp(sttr,dest,array));
     }

    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        for(int i = 1,j = 0; i < dest.length();i++){

            while(j > 0 && dest.charAt(j) != dest.charAt(i)){
                j = next[j-1]; //计算出j前面有多少个字符是收尾相同的个数。
            }
            //1 如果相等
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            //2 把j赋值给数组
            next[i] = j;
        }
        return next;
    }

    public static int kmp(String str,String dest,int[] next){
        for(int i = 0,j = 0; i < str.length();i++){

            while(j > 0 && str.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            if(str.charAt(i) == dest.charAt(j)){
                j++;
            }
            if(j == dest.length()){
                return i-j+1;  //返回初始的位置。
            }
        }
        return -1;
    }

}
