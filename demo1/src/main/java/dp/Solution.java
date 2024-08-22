package dp;

import java.util.Arrays;

/**
 * @Description: 力扣 72：编辑距离 (D[i][j] 表示 A 的前 i 个字母和 B 的前 j 个字母之间的编辑距离)
 * @Author: ls
 * @Date: 2024/7/2712:32
 */
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        int[][] D = new int[n+1][m+1];

        // 边界状态初始化
        for(int i = 0; i < n+1; i++){
            D[i][0] = i;
        }
        for(int j = 0; j < m+1; j++){
            D[0][j] = j;
        }

        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < m+1; j++){
                int left = D[i-1][j] + 1;
                int down = D[i][j-1] + 1;
                int left_down = D[i-1][j-1];
                if(word1.charAt(i-1) != word2.charAt(j-1)){
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

    }
}