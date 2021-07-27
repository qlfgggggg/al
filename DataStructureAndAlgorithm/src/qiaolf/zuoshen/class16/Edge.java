package qiaolf.zuoshen.class16;

/**
 * @Description: 图的边
 * Author:qiaolf
 * @Date:2021/4/6 14:22
 **/
public class Edge {
    public int weight;//边的权重
    public Node from; //从哪个节点出发
    public Node to;//到哪个节点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
