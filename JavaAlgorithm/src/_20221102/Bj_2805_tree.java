package _20221102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_2805_tree {
    static int N,M;
    static int[] map;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //나무 수
        M = Integer.parseInt(st.nextToken()); //집으로 가져가는 나무 길이

        map = new int[N]; //1부터 시작
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        int min = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(map[i], max);
        }

        while(min < max){
            int mid = (min+max)/2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                if(map[i] > mid){
                    sum += (map[i]-mid);
                }
            }
            if(sum>M){
                min = mid+1;
            }else{
                max = mid;
            }
        }
        System.out.println(min);
    }
}
/*
5 2000000000
900000000 900000000 900000000 900000000 900000000
ans: 500000000
=> OutOfMemoryError: heap space
 */
