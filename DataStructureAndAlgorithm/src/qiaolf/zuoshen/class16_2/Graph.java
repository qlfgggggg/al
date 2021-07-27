package qiaolf.zuoshen.class16_2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/5/24 9:20
 **/
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes=new HashMap<>();
        this.edges=new HashSet<>();
    }
}
