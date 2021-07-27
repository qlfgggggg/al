package qiaolf.offer;

/**
 * @Description: 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 * Author:qiaolf
 * @Date:2021/6/1 10:29
 **/

/**
 * 我的思想：我这里使用的是以行和列   从下往上，从左到右
 */
public class Offer04 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i=matrix.length-1;
        int j=0;
        while (true){
            if (i<0){
                break;
            }
            if (j>=matrix[0].length){
                break;
            }
            if (matrix[i][j]==target){
                return true;
            }else if (matrix[i][j]>target){
                i--;
            }else if (matrix[i][j]<target){
                j++;
            }
        }
        return false;
    }
}
