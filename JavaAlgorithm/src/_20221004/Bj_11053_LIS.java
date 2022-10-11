package _20221004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_11053_LIS {
    static int N;
    static int[] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[1001];
        dp = new int[1001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int idx = -1;
            int maxdp = -1;
            for (int j = 0; j < i; j++) {
//                if(map[i]>map[j]){
//                    dp[i] = Math.max(dp[j]+1, dp[i]);
//                }else{
//                    dp[i] = 1;
//                }
                if(dp[j] > maxdp && map[i] > map[j]){
                    maxdp = dp[j];
                    idx = j;
                }
            }
            dp[i] = dp[idx] + 1;
        }
        int result = -1;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
/*
20
31 84 18 62 35 77 23 53 60 94 3 77 60 51 42 18 83 85 63 51
ans: 7
 */