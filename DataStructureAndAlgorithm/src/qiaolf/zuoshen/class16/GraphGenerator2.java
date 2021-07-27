package qiaolf.zuoshen.class16;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/4/7 9:23
 **/
public class GraphGenerator2 {

    public static Graph getGaraph(int[][] maxtire){
        Graph graph = new Graph();
        for (int i = 0; i < maxtire.length; i++) {
            int weight = maxtire[i][0];
            int from = maxtire[i][1];
            int to = maxtire[i][2];
            if (!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if (!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }
}
