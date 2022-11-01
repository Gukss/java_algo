package _20221029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_2110_install {
    static int N,C;
    static int[] map;

    public static void start(int left, int right, int depth){
        //2, 3, 4,5, 6,7, 8,9 ...
        //   1  2    3    4
        if(depth == (C/2)+1){
            System.out.println(Math.abs(map[left] - map[right]));
            return;
        }
        int idx = left;
        int diff = Integer.MAX_VALUE;
        boolean rl = true; //왼쪽-true, 오른쪽-false
        if(left + 1 >= N) return;
        for (int i = left+1; i < right; i++) {
            int dl = Math.abs(map[left] - map[i]);
            int dr = Math.abs(map[right] - map[i]);
            if(dl > dr){
                if(diff > dl){
                    diff = dl;
                    idx = i;
                    rl = true;
                }
            }else{
                if(diff > dr){
                    diff = dr;
                    idx = i;
                    rl = false;
                }
            }
        }
        if(rl){ //왼쪽
            start(left, idx, depth+1);
            return;
        }else{
            start(idx, right, depth+1);
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //집 개수
        C = Integer.parseInt(st.nextToken()); //공유기 개수

        map = new int[N];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(map);

        if(C==2){
            System.out.println(Math.abs(map[0] - map[1]));
        }else{
            int diff = Integer.MAX_VALUE;
            int idx = 0;
            for (int i = 1; i < N-1; i++) {
                if(diff > Math.max(Math.abs(map[0] - map[i]), Math.abs(map[N-1] - map[i]))){
                    diff = Math.max(Math.abs(map[0] - map[i]), Math.abs(map[N-1] - map[i]));
                    idx = i;
                }
            }
            if(C==3){
                System.out.println(Math.min(Math.abs(map[0] - map[idx]), Math.abs(map[N-1] - map[idx])));
            }else{
                start(0, N-1, 1);
            }
        }
    }
}
