package qiaolf.zuoshen.class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/4/6 14:22
 **/
public class Graph {
    //每个节点的编号
    public HashMap<Integer,Node> nodes;
    //所有的边
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
