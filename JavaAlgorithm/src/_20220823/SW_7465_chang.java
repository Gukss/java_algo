package _20220823;

import java.util.ArrayList;
import java.util.Scanner;

public class SW_7465_chang {
    static int[][] list;
    static int[] parents;

    public static int find(int a){
        if(parents[a] == a){
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot){
            return false;
        }
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            list = new int[M][2];

            parents = new int[N+1];
            for (int i = 1; i <= N ; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                list[i][0] = a;
                list[i][1] = b;

            }

            for(int[] cur : list){
                union(cur[0], cur[1]);
            }
            int result = 0;
            for (int i = 1; i <= N; i++) {
                if(parents[i] == i){
                    result += 1;
                }
            }
            System.out.printf("#%d %d\n",test_case, result);
        }
    }
}
