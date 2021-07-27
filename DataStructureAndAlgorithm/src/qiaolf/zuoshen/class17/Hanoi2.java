package qiaolf.zuoshen.class17;

/**
 * @Description: 用递归的方式来解决汉诺塔问题
 * Author:qiaolf
 * @Date:2021/6/1 9:58
 **/
public class Hanoi2 {

    public static void main(String[] args) {
        Hanoi2 hanoi2 = new Hanoi2();
        hanoi2.hanoi(3);
    }

    public void hanoi(int n){
        if (n>0){
            fun(n,"left","right","mid");
        }
    }

    private void fun(int n, String from, String to, String other) {
        if (n==1){
            System.out.println("move 1 from "+from+" to "+to);
        }else{
            fun(n-1,from,other,to);
            System.out.println("move "+n+" from "+from+" to "+to);
            fun(n-1,other,to,from);
        }
    }

}
