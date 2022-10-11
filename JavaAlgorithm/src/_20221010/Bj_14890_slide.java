package _20221010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_14890_slide {
    static int N,L;
    static int[][] map;
    static boolean[] v;
    static int result;

    public static boolean check(int r, int c, int dir){
        v = new boolean[N];
        int[] t = new int[N];

        if(dir == 0){ //행방향
            for (int i = 0; i < N; i++) {
                t[i] = map[r][i];
            }
        }else{ //열방향
            for (int i = 0; i < N; i++) {
                t[i] = map[i][c];
            }
        }

        for (int i = 0; i < N-1; i++) {
            if(t[i] == t[i+1]){ //높이가 같다.
                continue;
            }
            if(Math.abs(t[i]-t[i+1]) > 1){ //경사로 못 놓을 때
                return false;
            }
            if(t[i]-1 == t[i+1]){ //다음이 낮은 경우
                for (int j = i+1; j <= i+L; j++) {
                    if(j >= N || t[i+1]!=t[j] || v[j]){ //벗어나고, 1차아니고, 경사로가 있으면
                        return false;
                    }
                    v[j] = true;
                }
            }
            if(t[i]+1 == t[i+1]){ //다음이 높은 경우
                for (int j = i; j > i-L; j--) {
                    if(j < 0 || t[i]!=t[j] || v[j]){ //벗어나고, 1차아니고, 경사로가 있으면
                        return false;
                    }
                    v[j] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        result = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            if(check(i,0,0)){ //dir:0 => 행방향
                result += 1;
            }
            if(check(0,i,1)){ //dir:1 => 열방향
                result += 1;
            }
        }
        System.out.println(result);
    }
}
