package qiaolf.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/4/7 10:25
 **/
public class solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
//    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
//        // write your code here
//
//    }
}



class DirectedGraphNode {
    int label;
    List<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}

