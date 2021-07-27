package qiaolf.DoubleLinkedList;

/**
 * @Description: TODO
 * Author:qiaolf
 * @Date:2021/4/3 22:33
 **/
public class solution1 {
    public boolean squareIsWhite(String coordinates) {
        char[] chars = coordinates.toCharArray();
        boolean flag = true;
            if ((chars[0]-'a')%2==0 ){
                if ((chars[1]-'0')%2==0){
                    flag = true;
                }else{
                    flag = false;
                }
            }else {
                if ((chars[1]-'0')%2!=0){
                    flag = true;
                }else{
                    flag = false;
                }
            }
            return flag;
    }
}
