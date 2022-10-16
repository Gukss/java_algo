package _20221015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_12865_knapsack {
    static int N,K;
    static int[][] map;
    static int[][] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][2];
        data = new int[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < K; j++) {
                if(map[i][0] > j){ //임시 배낭보다 작으면 => 임시 배낭이 작으면 => 무게가 임시배낭보다 크면
                    data[i][j] = data[i-1][j];
                }else{
                    data[i][j] = Math.max(data[i-1][j], data[i-1][j-map[i][0]] + map[i][1]);
                }
            }
        }


//
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= K; j++) { //임시 배낭 사이즈
//                if(map[i][0] > j){ //임시 배낭이 작으면
//                    data[i][j] = data[i-1][j];
//                }else{
//                    data[i][j] = Math.max(data[i-1][j], data[i-1][j-map[i][0]] + map[i][1]);
//                }
//            }
//        }
    }
}
