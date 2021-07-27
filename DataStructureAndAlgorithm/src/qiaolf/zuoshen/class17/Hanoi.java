package qiaolf.zuoshen.class17;

/**
 * @Description: 汉诺塔问题   递归问题
 * Author:qiaolf
 * @Date:2021/6/1 8:36
 **/
public class Hanoi {

    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        hanoi.leftToRight(3);
    }

    public void leftToRight(int n){
        if (n==1){
            System.out.println("move 1 from left to right");
            return;
        }
        leftToMid(n-1);
        System.out.println("move "+n+" from left to right");
        midToRight(n-1);
    }

    private void midToRight(int n) {
        if (n==1){
            System.out.println("move 1 form mid to right");
            return;
        }
        midToLeft(n-1);
        System.out.println("move "+n+" from mid to right");
        leftToRight(n-1);
    }

    private void midToLeft(int n) {
        if (n==1){
            System.out.println("move 1 from mid to left");
            return;
        }
        midToRight(n-1);
        System.out.println("move "+n+" from mid to left");
        rightToLeft(n-1);
    }

    private void rightToLeft(int n) {
        if (n==1){
            System.out.println("move 1 from right to left");
            return;
        }
        rightToMid(n-1);
        System.out.println("move "+n+" form right to left");
        midToLeft(n-1);
    }

    private void rightToMid(int n) {
        if (n==1){
            System.out.println("move 1 from right to mid");
            return;
        }
        rightToLeft(n-1);
        System.out.println("move "+n+" form right to mid");
        leftToMid(n-1);
    }

    private void leftToMid(int n) {
        if (n==1){
            System.out.println("move 1 from left to mid");
            return;
        }
        leftToRight(n-1);
        System.out.println("move "+n+" form left to mid");
        rightToMid(n-1);
    }
}
