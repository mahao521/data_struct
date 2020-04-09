package com.mahao.linkedlist.graph;

import java.util.Arrays;

/**
 * Created by mahao on 2018/4/15.
 */

public class Dijkstra {

    /**
     *   思考： 最短路径和prim  prim求的是最小连通图，是一个集合和另一个集合连通。。最短路径是从一个点出发，求到每个节点的最小值。
     */

    Graph graph;
    int[] vertices;
    int sourceP;
    int[] distance;
    int[][] edge;

    public Dijkstra(Graph g, int source){
        graph = g;
        sourceP = source;
        vertices = new int[graph.verticesSize];
        distance = new int[graph.verticesSize];
        edge = graph.matrix;
        calculate();
        System.out.println(Arrays.toString(distance));
    }

    /**
     *  最短路径edge[source][p]= 10  表示sour节点到p节点的边的权值为10
     */
    public void calculate(){

        int minDis;
        int u = 0;
        //初始化
        for(int i = 0;i < graph.verticesSize; i++){

            distance[i] = edge[sourceP][i];
            vertices[i] = 0;
        }
        vertices[sourceP] = 1;

        for(int i = 1; i < graph.verticesSize; i++){  //顶点比较测次数
            minDis = graph.MAX_WEIGHT;
            //查找最短的那条边
            //将找到的边对应的节点保存到u
            for(int j = 0; j < graph.verticesSize; j++){
                if(vertices[j] == 0 && distance[j] < minDis){
                    u = j;
                    minDis = distance[j];
                }
            }
            //说明找完了
            if(minDis == graph.MAX_WEIGHT){
                return ;
            }
            vertices[u] = 1;
            //更新distance,也就是起始点到其他未访问节点的距离
            for(int j = 0; j < graph.verticesSize; j++){

                //新节点u到未加入节点j有边---比较更新 sourcep 到 j 的距离。
                if(vertices[j] == 0 && edge[u][j] < graph.MAX_WEIGHT){
                    if(distance[u] + edge[u][j] < distance[j]){
                        distance[j] = distance[u] + edge[u][j];
                    }
                }
            }
        }
    }


}















