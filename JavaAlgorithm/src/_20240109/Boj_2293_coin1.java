package _20240109;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2293_coin1 {
	public static int N,K;
	public static int[] arr;
	public static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		dp = new int[K+1];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		

		//1,2,5로 생각하면
		//1일 때는 1~10까지 모두 1가지다.
		//2일 때는 2부터 1가지가 는다. 
	}
}
