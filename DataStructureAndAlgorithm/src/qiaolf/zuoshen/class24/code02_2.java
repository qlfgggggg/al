package qiaolf.zuoshen.class24;

import java.util.LinkedList;

/**
 * @Description: 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 * Author:qiaolf
 * @Date:2021/5/21 10:35
 **/
public class code02_2 {
    //滑动窗口来解题,需要一个最大值双端队列和最小值双端队列
    // 如果窗口的r边界已经不满足要求了，以l开头的滑动窗口再向右扩r++也不会满足要求
    //如果滑动窗口内max-min<=num,滑动窗口内的所有值都满足要求
    //以l为左边界的滑动窗口内有多少个子数组满足要求，l++
    public int test(int[] arr,int num){
        LinkedList<Integer> maxWindows = new LinkedList<>();
        LinkedList<Integer> minWindows = new LinkedList<>();
        int count=0;//符合要求的子数组的数目
        int r =0;//窗口的右边界
        int len = arr.length;
        //l是左边界,每次以l开头的左边界有多少个子数组满足要求
        for (int l = 0; l < len; l++) {
            while (r<len){
                //生成最大值双端队列   双端队列  大------>小
                while (!maxWindows.isEmpty() && arr[maxWindows.peekLast()]<=arr[r]){
                    maxWindows.pollLast();
                }
                maxWindows.add(r);
                //生成最小值双端队列   双端队列  小------>大
                while (!minWindows.isEmpty() && arr[minWindows.peekLast()]>=arr[r]){
                    minWindows.pollLast();
                }
                minWindows.add(r);
                //这个是判断是否r已经不满足要求了，如果满足要求r++，不满足要求就要进行break,进行外层l++
                //在滑动窗口内最大值减去最小值
                if (arr[maxWindows.peekFirst()]-arr[minWindows.peekFirst()]<=num){
                    r++;
                }else {
                    break;
                }
            }
            //判断有多少个以l开头的滑动窗口有多少个子数组满足要求
            count += r-l;
            //判断双端队列最大值 是否过期，什么时候过期？因为要进行l++了，所以如果最大值就是l说明最大值过期了
            if (maxWindows.peekFirst()==l){
                maxWindows.pollFirst();
            }
            //判断最小值是否过期
            if (minWindows.peekFirst()==l){
                minWindows.pollFirst();
            }
        }
        return count;
    }
}
