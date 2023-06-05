import java.util.*;

class Solution {
    /*
    dp
    dp[0][n] = land[0][n]
    dp[i][n] = max(dp[i-1][n제외]+land[i][n], ...)
    */
    public static int[][] dp, Land;
    public static int row;
    
    int solution(int[][] land) {
        Land = land;
        row = land.length;
        dp = new int[row][4];
        for(int i=0;i<4;i++){
            dp[0][i] = land[0][i];
        }
        
        for(int i=1;i<row;i++){
            for(int j=0;j<4;j++){
                dp[i][j] = Math.max(Math.max(dp[i-1][(j+1)%4], dp[i-1][(j+2)%4]), dp[i-1][(j+3)%4])+Land[i][j];
            }
            
        }
        // for(int i=0;i<4;i++){
        //     int max = Integer.MIN_VALUE;
        //     for(int j=1;j<row;j++){
        //         max = Math.max(Math.max(dp[j-1][(i+1)%4], dp[j-1][(i+2)%4]), dp[j-1][(i+3)%4]);
        //     }
        //     dp[j][i] = max;
        // }
        
        int answer = 0;
        for(int i=0;i<4;i++){
            // System.out.println(dp[row-1][i]);
            answer = Math.max(answer, dp[row-1][i]);
        }
        return answer;
    }
}