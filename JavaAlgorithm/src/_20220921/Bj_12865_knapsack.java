package _20220921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_12865_knapsack {
    static int N,K;
    static int[][] s;
    static int[][] data;

    public static void knap(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) { //임시 배낭 용량
                if(s[i][0] > j){
                    data[i][j] = data[i-1][j];
                }else{
                    data[i][j] = Math.max(data[i-1][j], data[i-1][j-s[i][0]]+s[i][1]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        s = new int[N+1][2];
        data = new int[N+1][100001]; //N과 K 모두 1부터 시작

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            s[i][0] = Integer.parseInt(st.nextToken()); //무게w
            s[i][1] = Integer.parseInt(st.nextToken()); //가치v
        }

        knap();
        System.out.println(data[N][K]);
    }
}
