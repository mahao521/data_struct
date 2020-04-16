package com.mahao.linkedlist.LongComSub;

import android.text.InputFilter;

/**
 * Created by mahao on 2018/5/6.
 */

//比dijtl更好的求最短路径的算法
public class Floyd {

    public static final int INF = Integer.MAX_VALUE;
    //邻接矩阵
    public static int[][] d = new int[][]{
            {0, 2, 1, 5},
            {2, 0, 4, INF},
            {1, 4, 0, 3},
            {5, INF, 3, 0}
    };
    public static int[][] p = new int[][]{
            {0, 1, 2, 3},
            {0, 1, 2, 3},
            {0, 1, 2, 3},
            {0, 1, 2, 3}
    };

    /**
     * 外层控制 节点 1，2，3，4
     * <p>
     * 内层控制 数组小标，当前位置距离 > 通过k节点到达j节点的距离，更新P集合。
     */
    public static void floyd() {

        for (int k = 0; k < d.length; k++) {

            for (int i = 0; i < d.length; i++) {   //0
                for (int j = 0; j < d.length; j++) { //3
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        //记录下路径
                        p[i][j] = p[i][k];
                    }
                }
            }
        }
        printArray(p);
        printArray(d);
    }

    /**
     * 打印路径
     */
    public static void printShortPath() {
        for (int i = 0; i < d.length; i++) {
            //通过序列号找到原来的一组路径
            for (int j = 0; j < d.length; j++) {
                System.out.print("V" + i + "->j" + j + "weigh:" + d[i][j] + "path:" + i);
                int k = p[i][j];
                while (k != j) {
                    System.out.print("->" + k);
                    k = p[k][j];
                }
                System.out.println();
            }
        }
    }

    /**
     * 打印二维数组
     *
     * @param array
     */
    public static void printArray(int[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------");
    }


    public static void main(String[] args) {

        floyd();
        printShortPath();
    }
}
