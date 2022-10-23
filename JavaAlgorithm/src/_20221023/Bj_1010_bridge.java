package _20221023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_1010_bridge {
    static int T,N,M;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dp = new int[M+1][M+1];

            for(int i=0;i<=M;i++) {
                dp[i][0]=1;
            }
            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= M; j++) {
                    if(i==j){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                    }
                }
            }
            sb.append(dp[M][N]+"\n");
        }
        System.out.println(sb);
    }
}
