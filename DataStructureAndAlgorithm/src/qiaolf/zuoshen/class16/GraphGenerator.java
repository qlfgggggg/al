package qiaolf.zuoshen.class16;

import java.lang.management.GarbageCollectorMXBean;

/**
 * @Description: 图的表示有邻接矩阵，邻接表表示的方法,我们将这些方式转变为我们自己的表示方式
 * Author:qiaolf
 * @Date:2021/4/6 14:34
 **/
public class GraphGenerator {

    //matrix,所有的边
    //n*3矩阵
    //[weigth,from,to]这代表的是边
    //[7,0,7]
    //[5,0,4]
    public static Graph createGraph(int[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
                int weigth = matrix[i][0];
                int from = matrix[i][1];
                int to = matrix[i][2];
                if (!graph.nodes.containsKey(from)){
                    graph.nodes.put(from,new Node(from));
                }
                if (!graph.nodes.containsKey(to)){
                    graph.nodes.put(to,new Node(to));
                }
            Node f = graph.nodes.get(from);
            Node t = graph.nodes.get(to);
            Edge edge=new Edge(weigth,f,t);
            f.nexts.add(t);
            f.out++;
            t.in++;
            graph.edges.add(edge);
            f.edges.add(edge);
        }
        return graph;
    }

}
