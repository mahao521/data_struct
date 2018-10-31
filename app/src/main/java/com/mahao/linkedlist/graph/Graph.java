package com.mahao.linkedlist.graph;


import java.util.LinkedList;

/**
 * Created by mahao on 2018/4/14.
 */

public class Graph {

    public int[] vertices;  //顶点
    public int[][] matrix;  //图的节点的边
    public int verticesSize; //顶点的数量

    public static final int MAX_WEIGHT = 0xFFF8;
    private boolean[] isVisited;

    public Graph(int verticesSize){

        this.verticesSize = verticesSize;
        vertices = new int[verticesSize];
        matrix = new int[verticesSize][verticesSize];
        isVisited = new boolean[verticesSize];
        for(int i = 0; i < verticesSize; i++){
            vertices[i] = i;
        }
    }


    /**
     *  计算v1 到 v2的长度
     * @param v1
     * @param v2
     * @return
     */
    public int getWidget(int v1,int v2){

        int weight  = matrix[v1][v2];
        return weight == 0 ? 0 : (weight == MAX_WEIGHT ? -1 : weight);
    }


    /**
     *  获取所有的顶点
     * @return
     */
    public int[] getVertices(){
        return vertices;
    }

    /**
     * 获取顶点v的出度
     * @param v
     */
    public int getOutDegree(int v){

        int count = 0;
        for(int i = 0; i < verticesSize; i++){

            if(matrix[v][i] != 0 && matrix[v][i] != MAX_WEIGHT){
                count++;
            }
        }
        return count;
    }

    /**
     *   获取顶点v入度
     * @param v
     * @return
     */
    public int getInDegree(int v){

        int count = 0;
        for(int i = 0; i < verticesSize; i++){

            if(matrix[i][v] != 0 && matrix[i][v] != MAX_WEIGHT){
                count++;
            }
        }
        return count;
    }


    /**
     *  获取某个顶点的第一个临接点
     * @param v
     * @return
     */
    public  int getFirstNeightbor(int v){

        for(int i = 0; i < verticesSize; i++){
            if(matrix[v][i] > 0 && matrix[v][i] != MAX_WEIGHT){
                return i;
            }
        }
        return -1;
    }


    /**
     *  查找节点v的ling'bia@param v
     * @param index
     * @return
     */
     public  int getFirstNeightborNext(int v,int index){

         if(index >= verticesSize){
             return -1;
         }
         for(int i = index +1; i < verticesSize ; i++){

             if(matrix[v][i] > 0 && matrix[v][i] != MAX_WEIGHT){

                 return i;
             }
         }
         return -1;
     }


    /**
     *  图的遍历
     */
    public void bfs(){

        for(int i = 0; i < vertices.length; i++){
            isVisited[i] = false;
        }

        for(int i = 0; i < verticesSize; i++){

            if(!isVisited[i]){
                isVisited[i] = true;
                System.out.println("vesited vertice : " + i);
                bfs(i);
            }
        }
    }

    public void bfs(int i){

        LinkedList<Integer> queue = new LinkedList<>();
        //访问第一个邻接点
        int fn = getFirstNeightbor(i);
        if(fn == -1){
            return;
        }
        if(!isVisited[fn]){
            isVisited[fn] = true;
            System.out.println("visited vertice:" + fn);
            queue.offer(fn);
        }
        //访问其他邻接点
        int next = getFirstNeightborNext(i,fn);
        while (next != -1){

            if(!isVisited[next]){
                isVisited[next] = true;
                System.out.println("visited vertice:" + next);
                queue.offer(next);
            }
            next = getFirstNeightborNext(i,next);
        }

        //当前节点访问完了，以其他节点为访问目标，访问。
        while (!queue.isEmpty()){
            int otherPoint = queue.poll();
            bfs(otherPoint);
        }
    }


    /**
     *   prim 和 最短路径的区别
     *   二者的不同之处在于“权值最低”的定义不同，
     *   Prim的“权值最低”是相对于U中的任意一点而言的，也就是把U中的点看成一个整体，
     *   每次寻找V-U中跟U的距离最小（也就是跟U中任意一点的距离最小）的一点加入U；
     *   而Dijkstra的“权值最低”是相对于v0而言的，也就是每次寻找V-U中跟v0的距离最小的一点加入U;
     *
     *   第1步：所有的点都在集合B中，A集合为空。

         第2步：任意以一个点为开始，把这个初始点加入集合A中，从集合B中减去这个点（代码实现很简单，也就是设置一个标示数组，为false表示这个点在B中，为true表示这个点在A中），
         寻找与它相邻的点中路径最短的点，如后把这个点也加入集合A中,从集合B中减去这个点（代码实现同上）。

         第3步：集合A中已经有了多个点，这时两个集合A和B，只要找到A集合中的点到B集合中的点的最短边，可以是A集合中的与B集合中的点的任意组合，把这条最短边有两个顶点，把在集合B中的顶点加入到集合A中，（
         代码实现的时候有点技巧，不需要枚举所有情况，也就是更新操作）。

         第4步：重复上述过程。一直到所有的点都在A集合中结束。
     *
     */
    public void prim(){

       int[] lowcost = new int[verticesSize];
       for(int i = 0; i < verticesSize; i++){
           lowcost[i] = matrix[0][i];
       }

       int min;
       int minIndex;
       int sum = 0;
       for(int i = 0; i < verticesSize; i++){
           min = MAX_WEIGHT;
           minIndex = 0;
           //从剩下节点到已经被查找节点集合U中最短的边的节点
           for(int j = 1; j < verticesSize; j++){

               if(lowcost[j] < min && lowcost[j] > 0){
                   min = lowcost[j];
                   minIndex = j;
               }
           }
           if(min == MAX_WEIGHT){
               return;
           }
           sum += min;
           //step2 : 找到这个节点后，更新剩下来的节点到已经被查找节点集合U的距离
           lowcost[minIndex] = 0;
           for(int z = 1; z < verticesSize; z++){
               if(lowcost[z] != 0 && matrix[minIndex][z] < lowcost[z]){
                   lowcost[z]  = matrix[minIndex][z];
               }
           }
           System.out.println("sum = " + sum);
       }

    }



    public static void main(String[] args){

/*      Graph graph = new Graph(5);
      int[] v0 = new int[]{0,1,1,MAX_WEIGHT,MAX_WEIGHT};
      int[] v1 = new int[]{MAX_WEIGHT,0,MAX_WEIGHT,1,MAX_WEIGHT};
      int[] v2 = new int[]{MAX_WEIGHT,MAX_WEIGHT,0,MAX_WEIGHT,MAX_WEIGHT};
      int[] v3 = new int[]{1,MAX_WEIGHT,MAX_WEIGHT,0,MAX_WEIGHT};
      int[] v4 = new int[]{MAX_WEIGHT,MAX_WEIGHT,1,MAX_WEIGHT,0};
      graph.matrix[0] = v0;
      graph.matrix[1] = v1;
      graph.matrix[2] = v2;
      graph.matrix[3] = v3;
      graph.matrix[4] = v4;

      graph.bfs(); //v0 v1 v2 v3 v4*/

      Graph graph = new Graph(7);
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
      //graph.prim();

      Dijkstra dijkstra = new Dijkstra(graph,0);

    }
}






