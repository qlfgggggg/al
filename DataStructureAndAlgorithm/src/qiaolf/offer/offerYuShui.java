package qiaolf.offer;

/**
 * @Description: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *  
 *
 * 提示：
 *
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Author:qiaolf
 * @Date:2021/7/27 15:57
 **/
public class offerYuShui {
    //要想找到能够接多少雨水   需要找到i下标左侧leftMax[i] 和rightMax[i]
    public int trap(int[] height) {
        if (height==null || height.length<3){
            return 0;
        }
        int N = height.length;
        int leftMax[] = new int[N];
        int rightMax[] = new int[N];
        leftMax[0]=height[0];
        rightMax[N-1]=height[N-1];
        int leMax = height[0];
        for (int i = 1; i < N; i++) {
            leMax = Math.max(height[i], leMax);
            leftMax[i]=leMax;
        }
        int riMax=height[N-1];
        for (int i = N-2; i>=0; i--) {
            riMax=Math.max(riMax,height[i]);
            rightMax[i]=riMax;
        }
        int yu =  0;
        for (int i = 1; i <N-1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            yu+=min-height[i];
        }
        return yu;
    }
}
