package day008;

import java.util.*;

public class SW_5215_hamburger {

    static int[][] in;
    static boolean visited[];
    static int L, N;
    static int result;
    public static void pow(int idx){
        if(idx == N){
            int ssum = 0;
            int ksum = 0;
            for (int i = 0; i < N; i++) {
                if(visited[i]){ //따로 저장하지 않고 함수 안에서 최대값을 구해야한다. 시간 생각
                    ssum += in[i][0];
                    ksum += in[i][1];
                }
            }
            if(ksum < L) result = Math.max(result, ssum);
            return;
        }
        visited[idx] = true;
        pow(idx+1);
        visited[idx] = false;
        pow(idx+1);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {

            /////////////////////////////////////////////////////////////////////////////////////////////
			N = sc.nextInt();
            L = sc.nextInt();

            in = new int[N][2];
            visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                in[i][0] = sc.nextInt();
                in[i][1] = sc.nextInt();
            }

            result = 0; //result 초기화 해주기
            pow(0);
            System.out.printf("#%d %d\n", test_case, result);
            /////////////////////////////////////////////////////////////////////////////////////////////

        }
    }
}
