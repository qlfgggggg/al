package qiaolf.zuoshen.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 最大线段重合问题
 * Author:qiaolf
 * @Date:2021/3/26 16:17
 **/
/*
* 给定很多线段，每个线段都有两个数[start, end]，
* 表示线段开始位置和结束位置，左右都是闭区间
规定：
1）线段的开始和结束位置一定都是整数值
2）线段重合区域的长度必须>=1
返回线段最多重合区域中，包含了几条线段
*
* 注：只有一个端点重合不算
* 步骤：
* 将线段按照开始位置从小到大排序，将拍好序的线段的结束位置装入小根堆，如果下一个线段的开始位置大于小根堆里的结束位置弹出结束位置，
* 最后小根堆里剩多少个数就有多少个最多重合的线段:线段比较大小：实现比较器
*
* */
public class code01_maxLine {
    public static class Line{
        public int start;
        public int end;
        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static class EndComparator implements Comparator<Line>{
        @Override
        public int compare(Line o1, Line o2) {
            return o1.end-o2.end;
        }
    }

    public static class StartComparator implements Comparator<Line>{
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start-o2.start;
        }
    }

    public static int maxCover(int[][] m){
        Line[] lines = new Line[m.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i]=new Line(m[i][0],m[i][1]);
        }
        //将线段按照开始位置从小到大排好序
        Arrays.sort(lines,new StartComparator());
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max=0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && lines[i].start>=heap.peek()){
                heap.poll();
            }
            heap.add(lines[i].end);
            //每次加一次线段后都要找出做大值
            max=Math.max(max,heap.size());
        }
        return max;
    }

    public static int maxCover2(int[][] m){
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < m.length; i++) {
            max=Math.max(max,m[i][1]);
            min=Math.min(min,m[i][0]);
        }
        int cover=0;
        for (double i = min+0.5; i <=max-0.5; i+=1) {
            int cur=0;
            for (int j = 0; j <m.length; j++) {
                if (m[j][0]<i && m[j][1]>i){
                    cur++;
                }
            }
            //这里这么写的原因是：比如：上次经过5.5的有10个cover代表的是上次的值，这次经过6.5的cur为11个，这次就取更大的作为cover，而不是把只要经过这些点
            //的数进行相加
            cover=Math.max(cover,cur);
        }
        return cover;
    }
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {

//        Line l1 = new Line(4, 9);
//        Line l2 = new Line(1, 4);
//        Line l3 = new Line(7, 15);
//        Line l4 = new Line(2, 4);
//        Line l5 = new Line(4, 6);
//        Line l6 = new Line(3, 7);
//
//        // 底层堆结构，heap
//        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
//        heap.add(l1);
//        heap.add(l2);
//        heap.add(l3);
//        heap.add(l4);
//        heap.add(l5);
//        heap.add(l6);
//
//        while (!heap.isEmpty()) {
//            Line cur = heap.poll();
//            System.out.println(cur.start + "," + cur.end);
//        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}
