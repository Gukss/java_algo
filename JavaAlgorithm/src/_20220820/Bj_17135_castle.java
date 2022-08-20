package _20220820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_17135_castle {
    static int[] sel;
    static int N,M,D;
    static int[][] map;
    static int[][] gmap;
    static boolean[] v;
    static int count = Integer.MIN_VALUE;
    static int result = Integer.MIN_VALUE;
    static int total;

    public static boolean check(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(gmap[i][j] == 1){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean distance(int i, int j, int x, int y){
        if(Math.abs(i - x) + Math.abs(j - y) <= D){
            return true;
        }else{
            return false;
        }
    }

    public static void pull(){
        for (int x = N-1; x >= 0 ; x--) { //한 줄 앞으로 당기기
            for (int y = 0; y < M; y++) {
                if(x != 0){
                    gmap[x][y] = gmap[x-1][y];
                }else{
                    gmap[x][y] = 0;
                }
            }
        }
        v = new boolean[3];
    }

    public static void game(){
        for (int i = N-1; i >= 0 ; i--) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 3; k++) { //궁수 위치만큼 반복
                    if(gmap[i][j] == 1){
                        if(distance(N-1-i, j, -1, sel[k]) && !v[k]){
                            v[k] = true;
                            gmap[i][j] = 0;
                            count += 1;
                        }
                    }
                }
            }
        }


    }

    public static void comb(int idx, int start){
        if(idx == sel.length){
            count = 0;
            gmap = map.clone();
            while(check()){
                game();
                pull();
            }
            result = Math.max(count, result);
//            System.out.println(Arrays.toString(sel));
            return;
        }
        for (int i = start; i < M; i++) {
            sel[idx] = i;
            comb(idx+1, i+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        gmap = new int[N][M];
        v = new boolean[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                //gmap[i][j] = map[i][j];
            }
        }
        sel = new int[3];
        comb(0, 0);
        System.out.println(result);
    }
}
