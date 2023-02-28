package _20230301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1912_continue {
	public static int n;
	public static long[] arr;
	public static long[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new long[100001];
		dp = new long[100001];
		for(int i=0;i<n;i++){
			arr[i] = Long.parseLong(st.nextToken());
		}
		dp[0] = arr[0]; //1번째 부터 반복문 돌기위해 초기화 -> dp[i-1]을 확인해야한다.
		long result = dp[0];

		for(int i=1;i<n;i++){
			if(arr[i] > dp[i-1] + arr[i]){ //현재값>이전까지 누적해서 더한 값+현재값
				dp[i] = Math.max(arr[i], dp[i-1]); // => 이때까지 누적한 값 버리고 현재 부터 다시 누적한다.
			}else{ //현재값이 누적해더 더한값보다 작으면
				dp[i] = dp[i-1] + arr[i]; //계속 누적해서 더한다.
			}
			result = Math.max(dp[i], result);
		}
		// for(int i=0;i<10;i++){
		// 	System.out.println(dp[i]);
		// }
		System.out.println(result);
	}
}
//30분