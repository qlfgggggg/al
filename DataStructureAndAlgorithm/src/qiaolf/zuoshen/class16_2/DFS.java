package qiaolf.zuoshen.class16_2;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @Description: 图的深度优先遍历
 * Author:qiaolf
 * @Date:2021/5/24 10:08
 **/
public class DFS {
    public void dfs(Node start){
        Set<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        set.add(start);
        stack.push(start);
        System.out.println(start);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            //pop节点可能有很多个next,但是我这个方法是一条道走到黑(先管一条路径，其他路径再说)，一次只往栈里加一条路径，直接跳出该循环，并把弹出的节点放回栈中
            for (Node next : pop.nexts) {
                if (!set.contains(next)){
                    //为什么还要push已经弹出去的节点，因为为了实现一条道走到黑，某个节点可能有很多后继节点
                    //但是一次循环只push一个后继节点，其他后继节点没有进去，这里是实现以后后继节点可以进去
                    stack.push(pop);
                    stack.push(next);
                    System.out.println(next);
                    set.add(next);
                    break;//这个就是实现一条道走到黑的关键  每次循环只放入一个节点就跳出循环
                }
            }
        }
    }
}
