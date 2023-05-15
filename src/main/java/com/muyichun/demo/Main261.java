package com.muyichun.demo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 * @Author muyichun
 * @Date 2021-05-20 14:07:11$
 * 1. 是连通
 * 2. 不能有环
 */
public class Main261 {
    public boolean validTreeBFS(int n, int[][] edges) {
        //构建邻接矩阵
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }
        //打印出来看看
//        for (int[] graph1 : graph){
//            for (int graph2: graph1){
//                System.out.print(graph2);
//            }
//            System.out.println();
//        }
        //进行BFS
        Queue<Integer> queue = new LinkedList<>();
        //从任意元素开始，这里默认从第一个
        queue.add(0);
        HashSet<Integer> visitSet = new HashSet<>();
        while (!queue.isEmpty()) {
            //出队列
            Integer cur = queue.poll();
            //标记拜访
            visitSet.add(cur);
            //获取该节点的邻接点
            for (int i = 0; i < n; i++) {
                if (graph[cur][i] == 1) {
                    if (visitSet.contains(i)) {
                        return false;
                    }
                    visitSet.add(i);
                    //已访问过的节点删掉
                    graph[cur][i] = 0;
                    graph[i][cur] = 0;
                    queue.add(i);
                }
            }
        }
        return visitSet.size() == n;
    }
    public boolean validTreeDFS(int n, int[][] edges) {
        //构建邻接矩阵
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }
        //打印出来看看
//        for (int[] graph1 : graph){
//            for (int graph2: graph1){
//                System.out.print(graph2);
//            }
//            System.out.println();
//        }
        dfs(0, new HashSet<Integer>(), graph, n);
        if (result == true && record.size() == n) return true;
        return false;
    }
    //判断是否得出结论
    boolean flag = false;
    boolean result = true;
    HashSet<Integer> record = new HashSet<>();
    public void dfs(int cur, HashSet<Integer>rootVisitSet,int[][]graph, int n){
        if (flag) return;
        if (rootVisitSet.contains(cur)){
            result = false;
            flag = true;
            return;
        }
        record.add(cur);
        //开始深入遍历
        rootVisitSet.add(cur);
        for (int i = 0; i < n; i++){
            if (cur != i && graph[i][cur] == 1){
                graph[cur][i] = 0;
                graph[i][cur] = 0;
                dfs(i, (HashSet<Integer>) rootVisitSet.clone(), graph, n);
                graph[cur][i] = 1;
                graph[i][cur] = 1;
            }
        }
    }
    public static void main(String[] args) {
        int n = 5;
//        int[][]edges = {{0,1},{1,2},{2,3},{1,3},{1,4}};
//          int[][]edges = {{0,1},{2,3}};
//        System.out.println(new Main261().validTreeBFS(n,edges));
        int[][]edges = {{0,1},{0,2},{0,3},{1,4}};
        System.out.println(new Main261().validTreeDFS(n,edges));
    }
}