package qiaolf.leetcode;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。
 * 我们将这些回答放在answers数组里。
 *
 * 返回森林中兔子的最少数量。
 *
 * 示例:
 * 输入: answers = [1, 1, 2]
 * 输出: 5
 * 解释:
 * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 *
 * 输入: answers = [10, 10, 10]
 * 输出: 11
 *
 * 输入: answers = []
 * 输出: 0
 * 说明:
 *
 * answers的长度最大为1000。
 * answers[i]是在[0, 999]范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rabbits-in-forest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Author:qiaolf
 * @Date:2021/4/5 14:09
 **/
public class solution781 {
    public int numRabbits(int[] answers) {
        if (answers==null || answers.length==0){
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < answers.length; i++) {
            if (!map.containsKey(answers[i])){
                map.put(answers[i],1);
            }else {
                map.put(answers[i],map.get(answers[i])+1 );
            }
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int sum=0;
        for (Map.Entry<Integer, Integer> entry : entries) {
            double v = ((double) entry.getValue()) / ((double) (entry.getKey() + 1));
            int ceil = (int)Math.ceil(v);
            int v1 = (entry.getKey()+1) * ceil;
            sum+=v1;
        }
        return sum;
    }

    public static void main(String[] args) {
        solution781 solution781 = new solution781();
        int i = solution781.numRabbits(new int[]{1, 0, 1, 0, 0});
        System.out.println(i);
    }
}
