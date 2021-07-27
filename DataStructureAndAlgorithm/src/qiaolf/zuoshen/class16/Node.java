package qiaolf.zuoshen.class16;

import java.util.ArrayList;

/**
 * @Description: 图的节点
 * Author:qiaolf
 * @Date:2021/4/6 14:22
 **/
public class Node {
    public int value;//节点的值
    public int in;//几个入度，就是到该节点的边有几条
    public int out;//几个出度，就是从该节点出发的边有几条
    public ArrayList<Node> nexts;//   该节点的邻居，就是该节点指向的所有节点
    public ArrayList<Edge> edges;//从该节点出发有的所有边

    public Node(int value) {
        this.value = value;
        in = 0;
        out=0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
