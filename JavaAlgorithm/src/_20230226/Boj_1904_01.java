package _20230226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1904_01 {
	public static int N;
	public static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[1000001];
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i=3;i<=N;i++){
			dp[i] = (dp[i-1] + dp[i-2]) % 15746;
		}
	

		System.out.println(dp[N]);
	}
}
