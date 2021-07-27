package qiaolf.zuoshen.class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Description: 图的深度优先遍历
 * Author:qiaolf
 * @Date:2021/4/6 15:02
 **/
public class code02_DFS {

    public void dfs(Node start){
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            for (Node next : pop.nexts) {
                if (!set.contains(next)){
                    stack.push(pop);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
