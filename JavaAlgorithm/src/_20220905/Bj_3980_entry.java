package _20220905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_3980_entry {
    static int C;
    static int[][] map;
    static int[] sel;
    static boolean[] v;
    static int result;
    public static void perm(int idx){
        if(idx == 11){
            int sum=0;
            for (int i = 0; i < 11; i++) {
                sum += sel[i];
            }
            result = Math.max(result, sum);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if(map[idx][i] == 0){
                continue;
            }
            if(v[i]) continue;
            v[i] = true;
            sel[idx] = map[idx][i];
            perm(idx+1);
            v[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < C; i++) {
            map = new int[11][11];
            result = Integer.MIN_VALUE;
            for (int j = 0; j < 11; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 11; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            //일차원 배열에 하나씩 저장하면서 dfs 내려간다. 일차원 배열에 값이 있는 위친데 새로 방문한 값이 더 크면 위로 올라와서
            //순열 -> 11이니까 가능 -> 어떻게 중간에 끊지?? 0이 들어가면 끊는다.
            v = new boolean[11];
            sel = new int[11];
            perm(0);
            System.out.println(result);
        }
    }
}
//1시간 30분