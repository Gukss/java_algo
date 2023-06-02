package _20230423;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj_2156_wine {
	public static void main(String[] args) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			int[] dp = new int[n];
			int[] map = new int[n];
			for(int i=0;i<n;i++){
				map[i] = Integer.parseInt(br.readLine());
			}

			//oox
			//oxo
			//xoo

			dp[0] = map[0];
			dp[1] = map[0]+map[1];
			dp[2] = Math.max(dp[1], Math.max(map[0]+map[2], map[1]+map[2]));

			for(int i=3;i<n;i++){
				dp[i] = Math.max(dp[i-1], Math.max(dp[i-2]+map[i], dp[i-3]+map[i-1]+map[i]));
			}
			System.out.println(dp[n-1]);
    }
}
