package _20230118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_27210_god {
    public static int N;
    public static int[] arr;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < N; i++) {
            if(arr[i-1] == arr[i]){
                dp[i] = dp[i-1]+1;
                max = Math.max(max, dp[i]);
            }else{
                dp[i] = 1;
            }
        }
        System.out.println(max);
    }
}

//2 2 2 1 2 2
//1 2 3 2 3 4

//1 1 2 2 2
//1 2 1 0 0 => 1
//0 0 1 2 3 => 2

//2 2 1 2 2
//1 2 1 2 3
