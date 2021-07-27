package qiaolf.zuoshen.class16_2;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/5/24 9:20
 **/
public class Edge {
    public int weight;
    public Node fromNode;
    public Node toNode;

    public Edge(int weight, Node fromNode, Node toNode) {
        this.weight = weight;
        this.fromNode = fromNode;
        this.toNode = toNode;
    }
}
