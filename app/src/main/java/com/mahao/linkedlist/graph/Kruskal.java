package com.mahao.linkedlist.graph;

import android.support.annotation.IntegerRes;
import android.support.annotation.Size;
import android.widget.EdgeEffect;

import java.security.PublicKey;

/**
 * Created by mahao on 2018/4/15.
 */

public class Kruskal {

    /*克鲁斯卡尔算法从另一途径求网的最小生成树。假设连通网N=（V，{E}），
    则令最小生成树的初始状态为只有n个顶点而无边的非连通图T=（V，{∮}），
    图中每个顶点自成一个连通分量。在E中选择代价最小的边，
    若该边依附的顶点落在T中不同的连通分量上，则将此边加入到T中，否则舍去此边而选择下一条代价最小的边。依次类推，
    直至T中所有顶点都在同一连通分量上为止。*/

    public  static int verticeSize;
    private static int[][] matrix;
    private static int edgeSize;
    private static final  int MAX_WEIGHT = 0xFFF8;

    private static Edge[] edges;

    private Kruskal(int verticeSize){
        this.verticeSize = verticeSize;
        matrix = new int[verticeSize][verticeSize];
    }

    /**
     *  获取图中所有的边
     * @return
     */
    public static Edge[] getEdges(){

        int index = 0;
        Edge[] edges = new Edge[verticeSize*verticeSize];
        for(int i = 0; i < verticeSize; i++){

            for(int j = 0; j < verticeSize; j++){

                if(matrix[i][j] != 0 && matrix[i][j] != MAX_WEIGHT){
                    edges[index++] = new Edge(i,j,matrix[i][j]);
                }
            }
        }
        edgeSize = index;
        return edges;
    }


    public static  void kruskal(){

        int index = 0;
        edges = getEdges();
        Edge[] currEdge = edges;
        Edge[]  targetEdge = new Edge[edgeSize];
        //targetEdge 下标表示边的起始点，值表示的终点
        //targetEdge[2] = 15 表示节点2的到节点15 有边
        int target_ednge[] = new int[edgeSize];
        //对边排序------寻找最小的边。
        sortEdge(currEdge,edgeSize);
        for(int i = 0;i < edgeSize; i++){
            int p1 = currEdge[i].start;
            int p2 = currEdge[i].end;

            //查找p1 和 p2 是否连通
            int m = getEnd(target_ednge,p1);
            int n = getEnd(target_ednge,p2);
            if(m != n){
                targetEdge[index++] = currEdge[i]; //加入边
                if(m > n){  //从小到大的寻找。
                    int temp = n;
                    n = m;
                    m = temp;
                }
                target_ednge[m] = n;
            }
        }

        int length = 0;
        for(int i = 0; i < index;i++){
            length+=targetEdge[i].weight;
        }
        System.out.println("最小生成树的权值：" + length);
    }


    /**
     *    所有的点，通过连通分量，最后最大值，一定是一样的，，如果不一样，说明2个节点，或者两个图，没有连通。
     * @param target_edge
     * @param p
     * @return
     */
    public static int getEnd(int[] target_edge,int p){

        int i = p;
        while(target_edge[i] != 0){
            i = target_edge[i];
        }
        return i;
    }


    private static void sortEdge(Edge[] adges,int size){

         for(int i =0 ;i < size; i++){

             for(int j = i+1; j < size; j++){

                 if(adges[i].weight > adges[j].weight){
                     Edge temp = adges[i];
                     adges[i] = adges[j];
                     adges[j] = temp;
                 }
             }
         }
    }

    public static class Edge{

        int start;
        int end;
        int weight;

        public Edge(int start,int end,int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }


    public static void main(String[] args){

        Kruskal graph = new Kruskal(7);
        int[] v0 = new int[]{0,50,60,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
        int[] v1 = new int[]{50,0,MAX_WEIGHT,65,40,MAX_WEIGHT,MAX_WEIGHT};
        int[] v2 = new int[]{60,MAX_WEIGHT,0,52,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,45};
        int[] v3 = new int[]{MAX_WEIGHT,65,52,0,50,30,42};
        int[] v4 = new int[]{MAX_WEIGHT,40,MAX_WEIGHT,50,0,70,MAX_WEIGHT};
        int[] v5 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,30,70,0,MAX_WEIGHT};
        int[] v6 = new int[]{MAX_WEIGHT,MAX_WEIGHT,45,42,MAX_WEIGHT,MAX_WEIGHT,0};
        graph.matrix[0] = v0;
        graph.matrix[1] = v1;
        graph.matrix[2] = v2;
        graph.matrix[3] = v3;
        graph.matrix[4] = v4;
        graph.matrix[5] = v5;
        graph.matrix[6] = v6;
        graph.kruskal();
    }
}
