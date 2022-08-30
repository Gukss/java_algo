package _20220830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_14889_startandlink {

    static int[][] map;
    static int N;
    static int[] sel;
    static int s1, s2;
    static int min;
    static boolean[] v;

    public static void comb(int idx, int start){
        if(idx == N/2){
            s1 = 0;
            s2 = 0;
            v = new boolean[N];
            for (int i = 0; i < N/2; i++) {
                v[sel[i]] = true;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(i==j) continue;
                    if(v[i] && v[j]){
                        s1 += map[i][j];
                    }else if(!v[i] && !v[j]){
                        s2 += map[i][j];
                    }
                }
            }
            min = Math.min(min, Math.abs(s1-s2));
            return;
        }
        for (int i = start; i < N; i++) {
            sel[idx] = i;
            comb(idx+1, i+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        sel = new int[N];
        s1 = 0;
        s2 = 0;
        min = Integer.MAX_VALUE;
        v = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //조합 N개 중에 N/2개 뽑기
        comb(0, 0);
        System.out.println();
    }
}
