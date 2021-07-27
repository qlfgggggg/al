package qiaolf.offer;

/**
 * @Description: 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 *
 *
 *
 *  
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * board 和 word 仅由大小写英文字母组成
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Author:qiaolf
 * @Date:2021/6/1 11:50
 **/
public class Offer12_2 {


    public static boolean exist(char[][] board, String word) {
        if (board.length*board[0].length<word.length()){
            return false;
        }
//        boolean flag = false;
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]==chars[0]){
                    if (process(board,chars,i,j,0)>0){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int process(char[][] board, char[] chars, int i, int j,int index) {
        if (i<0 || i>=board.length || j<0 || j>=board[0].length){
            return 0;
        }
        if (index==chars.length-1 && board[i][j]==chars[index]){
            return 1;
        }
        if (board[i][j]!=chars[index]){
            return 0;
        }else{
            int p1 =process(board,chars,i+1,j,index+1);
            int p2 =process(board,chars,i-1,j,index+1);
            int p3 =process(board,chars,i,j+1,index+1);
            int p4 = process(board,chars,i,j-1,index+1);
            return p1+p2+p3+p4;
        }
    }

    public static void main(String[] args) {
        char[][] chars={{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCB";
        System.out.println(exist(chars,word));
    }
}
