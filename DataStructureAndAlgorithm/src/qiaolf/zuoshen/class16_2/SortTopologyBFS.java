package qiaolf.zuoshen.class16_2;

import java.util.*;

/**
 * @Description: 拓扑排序:拓扑序一定是有向无环图
 * Author:qiaolf
 * @Date:2021/5/24 14:22
 **/
public class SortTopologyBFS {
    //还是根据入度为0的点来解题
    class DirectedGraphNode {
         int label;
         List<DirectedGraphNode> neighbors;
         DirectedGraphNode(int x) {
             label = x;
             neighbors = new ArrayList<DirectedGraphNode>();
         }
     }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        HashMap<DirectedGraphNode,Integer> inMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            inMap.put(node,0);
        }

        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                inMap.put(neighbor,inMap.get(neighbor)+1);
            }
        }
        Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();
        for (DirectedGraphNode node : graph) {
            if (inMap.get(node)==0){
                zeroInQueue.add(node);
            }
        }
        ArrayList<DirectedGraphNode> res=new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            DirectedGraphNode poll = zeroInQueue.poll();
            res.add(poll);
            for (DirectedGraphNode neighbor : poll.neighbors) {
                inMap.put(neighbor,inMap.get(neighbor)-1);
                if (inMap.get(neighbor)==0){
                    zeroInQueue.add(neighbor);
                }
            }
        }
        return res;
    }

}
