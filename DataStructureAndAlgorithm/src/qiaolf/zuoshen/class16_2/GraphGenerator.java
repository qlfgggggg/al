package qiaolf.zuoshen.class16_2;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/5/24 9:33
 **/
public class GraphGenerator {

    //matrix,所有的边
    //n*3矩阵
    //[weigth,from,to]这代表的是边
    //[7,0,7]
    //[5,0,4]
    public Graph createGraph(int[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight=matrix[i][0];
            int from=matrix[i][1];
            int to=matrix[i][0];
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.in++;
            toNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }
}
