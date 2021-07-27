package qiaolf.zuoshen.class24;

/**
 * @Description: 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 * Author:qiaolf
 * @Date:2021/5/21 9:45
 **/

import java.util.LinkedList;

/**
 * 先说子数组：这个子数组在原数组中必须是连续的
 */
public class code02 {

    //用滑动窗口来解这道题
    //先说明：如果窗口内的最大值减去窗口内的最小值符合要求，窗口内的所有值都符合要求
    //以l为开始位置的窗口扩到r位置已经不达标了，再往后扩也都是不达标的了，所以扩到r位置停下来，l++，再开始试试
    public int test2(int arr[],int num){
        //
        int count = 0;
        int r=0;
        int len = arr.length;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        //每一次循环判断出以l位置开头的滑动窗口符合条件的子数组的数目
        for (int l = 0; l < len; l++) {
            //扩到r不达标的位置
            while (r<len){
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()]<=arr[r]){
                    maxWindow.pollLast();
                }
                maxWindow.add(r);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()]>=arr[r]){
                    minWindow.pollLast();
                }
                minWindow.add(r);
                //在这里是决定是否r++还是r已经不满足要求了，再向后扩也不满足要求了
                if (arr[maxWindow.peekFirst()]-arr[minWindow.peekFirst()]<=num){
                    r++;
                }else{
                    break;
                }
            }
            //每一次循环判断出以l位置开头的滑动窗口符合条件的子数组的数目
            count += r-l;
            //过期的数据清除出滑动窗口,因为要进行下一次l++了，当前l位置和r位置搭配已经不达标了
            if (maxWindow.peekFirst()==l){
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst()==l){
                minWindow.pollFirst();
            }
        }
        return count;
    }


}
