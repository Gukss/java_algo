package _20230227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj_9461_wave {
	public static int N,T;
	public static long[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		dp = new long[101];
		Arrays.fill(dp, -1);
		for(int i=0;i<T;i++){
			N = Integer.parseInt(br.readLine());
			dp[0] = 0;
			dp[1] = 1;
			dp[2] = 1;
			for(int j=3;j<=N;j++){
				if(dp[j] == -1){
					dp[j] = dp[j-2] + dp[j-3];
				}
			}
			sb.append(dp[N]+"\n");
		}
		System.out.println(sb);
	}
}
//20분