package qiaolf.zuoshen.class16_2;

import java.util.*;

/**
 * @Description: 拓扑排序
 * Author:qiaolf
 * @Date:2021/5/24 14:22
 **/
public class SortTopologyDFS {
    /**
     * 5:  x->a->b->c->d    x走过的节点比y走过的节点数多，x的拓扑序一定小于等于y，
     * 4:  y->f->g->h
     */

    /**
     * 可以根据当前点的点次来计算拓扑序
     */

    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    class Record{
        DirectedGraphNode cur;
        int deep;

        public Record(DirectedGraphNode cur, int deep) {
            this.cur = cur;
            this.deep = deep;
        }

    }

    public class MyComparator implements Comparator<Record>{

        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep-o1.deep;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode,Record> recordHashMap = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur,recordHashMap);
        }
        ArrayList<Record> list=new ArrayList<>();
        for (DirectedGraphNode node : graph) {
            list.add(recordHashMap.get(node));
        }
        //深度越大越排在前面，所以比较器大的排在前面   o2-o1
        list.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : list) {
            ans.add(record.cur);
        }
        return ans;
    }

    private Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> record) {
        if (record.containsKey(cur)){
            return record.get(cur);
        }
        //找到一个节点的最大深度
        int follow = 0;
        for (DirectedGraphNode neighbor : cur.neighbors) {
           follow=Math.max(follow,f(neighbor,record).deep);
        }
        Record ans = new Record(cur,follow+1);
        record.put(cur,ans);
        return ans;
    }
}
