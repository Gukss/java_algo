package _20230301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Boj_1149_RGB {
	public static int N;
	public static long[][] arr;
	public static long[][] dp;
	public static long turnSum;
	public static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new long[1001][3];
		dp = new long[1001][3];

		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Long.parseLong(st.nextToken());
			arr[i][1] = Long.parseLong(st.nextToken());
			arr[i][2] = Long.parseLong(st.nextToken());
		}

		dp[0][0] = arr[0][0]; //dp 0번 초기화
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];

		for(int i=1;i<N;i++){
			for(int j=0;j<3;j++){ //현재 칠해야하는 idx 피해서 이전에 최소였던 값에 현재 칠할 값을 더해서 dp테이블 만든다.
				dp[i][j] = Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]) + arr[i][j];
			}
		}
		// for(int i=0;i<N;i++){
		// 	System.out.println(Arrays.toString(dp[i]));
		// }
		
		result = Long.MAX_VALUE;
		for(int i=0;i<3;i++){
			result = Math.min(result, dp[N-1][i]);
		}
		System.out.println(result);
		
	}
}

//1시간 30분