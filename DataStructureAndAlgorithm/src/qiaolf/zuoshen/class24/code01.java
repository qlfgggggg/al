package qiaolf.zuoshen.class24;


import java.util.LinkedList;

/**
 * @Description: 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 * Author:qiaolf
 * @Date:2021/5/20 20:29
 **/
public class code01 {
    public static void main(String[] args) {
        int arr1[]={4,3,5,4,3,3,6,7};
        int arr[]={6,2,15,7,8,3,3,6,7};
        int w = 3;
//        for (int l = 0,r=l+w-1; l < arr.length-w+1 && r<arr.length;l++,r++) {
//            for (int i = l; i < ; i++) {
//                
//            }
//        }
        int res[]=new int[arr.length-w+1];
        for (int i = 0; i < arr.length-w+1; i++) {
            int max=arr[i];
            for (int j = i+1; j <=i+w-1 ; j++) {
                max=Math.max(max,arr[j]);
            }
            res[i]=max;
        }
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    public int[] sys(int[] arr,int w){
        if (arr==null || arr.length==0 || w<1){
            return null;
        }
        //滑动窗口的右侧边界
        int r=0;
        //存放在某个范围上的最大值的双端队列 从大到小
        LinkedList<Integer> qmax = new LinkedList<>();
        //要生成的数组 生成数组的长度
        int[] res=new int[arr.length-w+1];
        //数组的下标
        int index = 0;
        //每次r++都要选出一个在滑动窗口最大值
        while (r<arr.length){
            //每次r++都要判断 r索引位置对应的数组的值是否大于双端队列的最小值
            while (!qmax.isEmpty() && qmax.peekLast()<=arr[r]){
                qmax.pollLast();//如果双端队列的最小值小于 arr[r]就弹出
            }
            //把arr[r]加入双端队列
            qmax.add(r);//为什么是把下表加入双端队列而不是加值呢？加值的话无法判断过期
            //判断最大值是否过期  什么时候过期呢？当最大值等于 r-w时说明该值已经不在滑动窗口内了
            //过期就要弹出窗口
            while (!qmax.isEmpty() && qmax.peekFirst()==r-w){
                qmax.pollFirst();
            }
            //何时把窗口内的最大值装入生成的数组,第一次装入要保证r>=w-1，因为这个时候滑动窗口刚全部进入数组内部
            if (r>=w-1){
                res[index++]=qmax.peekFirst();
            }
        }
        return res;
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums==null || nums.length==0 || k<1){
            return null;
        }
        //滑动窗口的右侧边界
        int r=0;
        //存放在某个范围上的最大值的双端队列 从大到小
        LinkedList<Integer> qmax = new LinkedList<>();
        //要生成的数组 生成数组的长度
        int[] res=new int[nums.length-k+1];
        //数组的下标
        int index = 0;
        //每次r++都要选出一个在滑动窗口最大值
        while (r<nums.length){
            //每次r++都要判断 r索引位置对应的数组的值是否大于双端队列的最小值
            while (!qmax.isEmpty() && nums[qmax.peekLast()]<=nums[r]){
                qmax.pollLast();//如果双端队列的最小值小于 nums[r]就弹出
            }
            //把nums[r]加入双端队列
            qmax.offer(r);//为什么是把下表加入双端队列而不是加值呢？加值的话无法判断过期
            //判断最大值是否过期  什么时候过期呢？当最大值等于 r-w时说明该值已经不在滑动窗口内了
            //过期就要弹出窗口
            while (!qmax.isEmpty() && qmax.peekFirst()==r-k){
                qmax.pollFirst();
            }
            //何时把窗口内的最大值装入生成的数组,第一次装入要保证r>=w-1，因为这个时候滑动窗口刚全部进入数组内部
            if (r>=k-1){
                res[index++]=nums[qmax.peekFirst()];
            }
            r++;
        }
        return res;
    }
}
