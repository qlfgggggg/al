package qiaolf.leetcode;

/**
 * @Description: 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Author:qiaolf
 * @Date:2021/4/5 20:14
 **/
public class solution200 {
    public int numIslands(char[][] grid) {
        int island=0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    island++;
                    //感染的方式：只要该位置的值为'1‘,置为0，如果该位置的上下左右有为’1‘的全部置为0
                    inject(grid,i,j);
                }
            }
        }
        return island;
    }
    private void inject(char[][] grid, int i, int j) {
        if (i<0 || i==grid.length || j<0 || j==grid[0].length || grid[i][j]!='1'){
            return;
        }
        grid[i][j]=0;
        inject(grid,i-1,j);
        inject(grid,i,j-1);
        inject(grid,i+1,j);
        inject(grid,i,j+1);
    }
}
