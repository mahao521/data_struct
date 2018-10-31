package com.mahao.linkedlist.LongComSub;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by mahao on 2018/4/22.
 */

public class AStar {


    //思想： 从起点开始---寻找到终点最近的距离  = 当前点到周围点的距离  + 周围位置的点到最后点距离


    //用来存能走的路
    public static ArrayList<P>  openArray = new ArrayList<>();
    //保存所有不能走的路径
    public static ArrayList<P> closeArray = new ArrayList<>();
    //定义起点和终点
    public static P startPoint;
    public static P endPoint;


    public static int[][] map = new int[][]{

        {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
        {3,0,0,1,0,0,0,0,0,0,0,0,0,0,3,3},
        {3,3,0,3,3,3,3,3,3,3,3,3,3,0,3,3},
        {3,3,3,0,0,0,0,0,0,0,0,3,3,0,3,3},
        {3,3,3,3,0,3,3,3,3,3,0,3,3,0,3,3},
        {3,3,3,3,0,0,3,3,3,3,0,0,0,0,3,3},
        {3,3,3,3,3,0,0,0,0,0,0,0,3,0,3,3},
        {3,3,3,3,3,3,3,3,3,3,3,3,0,0,3,3},
        {3,3,3,3,3,3,3,3,3,3,3,3,3,2,3,3},
        {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
    };

    public static void init(){
        for(int i = 0; i < map.length;i++){
            for(int j = 0; j < map[i].length;j++){
                if(map[i][j] == 1){
                    openArray.add(new P(i,j));
                    startPoint = new P(i,j);
                }else if(map[i][j] == 3){
                    closeArray.add(new P(i,j));
                }else {
                    endPoint = new P(i,j);
                }
            }
        }
    }

    /**
     *    没走一步 计算改点到终点的距离（勾股定理）
     * @param startPoint
     * @param mideNode
     * @return
     */
    public static int g(P startPoint ,P mideNode){
        int b = Math.abs(startPoint.x - mideNode.x);
        int a = Math.abs(startPoint.y - mideNode.y);
        int c = (int) Math.sqrt(a*a + b*b);
        return c;
    }

    /**
     *   节点到终点的距离
     * @param midNode
     * @param endPoint
     * @return
     */
    public static int h(P midNode,P endPoint){
        int b = Math.abs(midNode.x - endPoint.x);
        int a = Math.abs(midNode.y - endPoint.y);
        int c = (int) Math.sqrt(a*a + b*b);
        return c;
    }


    /**
     *  总距离
     * @param start
     * @param mid
     * @param end
     * @return
     */
    public static int f(P start , P mid, P end){

        return g(start,mid) + h(mid,end);
    }

    //寻找路径
    public static void openFn(P p){
        //判断当前位置是不是终点
        if(p.x == endPoint.x && p.y == endPoint.y){
            return;
        }
        //标记当前位置
        map[p.x][p.y] = 1;
        //寻找相邻的位置，寻找下一个点
        P p2 = lookUp(p);
        //递归下一个位置
        openFn(p2);
    }

    //返回找到的下一个节点
    public static P lookUp(P p){

        //存放所有能走的路径
        ArrayList<P> result = new ArrayList<>();
        //开始搜寻周围能走的路
        if(map[p.x + 1][p.y] != 3 && map[p.x + 1][p.y] != 1){//下
            result.add(new P(p.x+1,p.y));
        }
        if(map[p.x][p.y+1] != 3 && map[p.x][p.y+1] != 1){//右
            result.add(new P(p.x,p.y+1));
        }
        if(map[p.x+1][p.y+1] != 3 && map[p.x+1][p.y+1] != 1){//右下
            result.add(new P(p.x+1,p.y+1));
        }
        if(map[p.x+1][p.y-1] != 3 && map[p.x+1][p.y-1] != 1){//左下
            result.add(new P(p.x+1,p.y-1));
        }
        if(map[p.x][p.y-1] != 3 && map[p.x][p.y-1] != 1){ //左
            result.add(new P(p.x,p.y-1));
        }
        if(map[p.x-1][p.y-1] != 3 && map[p.x-1][p.y-1] != 1){ //左上
            result.add(new P(p.x-1,p.y-1));
        }
        if(map[p.x-1][p.y] != 3 && map[p.x-1][p.y] != 1){ //左
            result.add(new P(p.x-1,p.y));
        }
        if(map[p.x-1][p.y+1] != 3 && map[p.x-1][p.y+1] != 1){ //左上
            result.add(new P(p.x-1,p.y+1));
        }
        //计算各点到最终的距离
        int temp[] = new int[result.size()];
        for(int i = 0; i < temp.length; i++){
            temp[i] = f(p,result.get(i),endPoint);
        }
        //找出数组中的最小值
        int index = 0;
        for(int i = 0; i < temp.length; i++){
            if(temp[index] > temp[i]){
                index = i;
            }
        }
        //保存估算最近到终点的那个点
        P minPoint = result.get(index);
        return minPoint;
    }


    public static class P{

        int x;
        int y;
        public P(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args){
        init();
        openFn(startPoint);
        for (int i = 0; i <map.length ; i++) {
            for (int j = 0; j < map[i].length; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}














