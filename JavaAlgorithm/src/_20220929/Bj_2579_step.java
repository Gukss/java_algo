package _20220929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj_2579_step {
    static int n;
    static int[] num, dp;

    public static int start(int idx){
        if(idx==1){
            return dp[idx];
        }else if(idx < 1){
            return 0;
        }
        if(dp[idx] == -1){
            return dp[idx] = Math.max(start(idx-3)+num[idx-1], start(idx-2)) + num[idx];
        }else{
            return dp[idx];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[301];
        dp = new int[301];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = num[1];
        start(n);
        System.out.println(dp[n]);
    }
}
/*
3
1
100
2
ans: 102
 */