package _20220906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_1799_bishop {
    static int N;
    static int[][] map;
    static ArrayList<Pos> list_b;
    static ArrayList<Pos> list_w;
    static boolean[] v;
    static int result_b;
    static int result_w;

    public static void power_b(int idx, int sum){
        if(idx == list_b.size()){ //마지막까지 오면
            if(result_b < sum){
                result_b = Math.max(result_b, sum); //결과값 갱신
            }
            return;
        }
        for (int i = 0; i < idx; i++) {
            if(v[i]){
                Pos prev = list_b.get(i);
                if(Math.abs(prev.r - list_b.get(idx).r) == Math.abs(prev.c - list_b.get(idx).c)){ //다음에 선택할 것과 이전에 선택한 것이 겹치면
                    v[idx] = false; //선택안하고
                    power_b(idx+1, sum); //다음으로
                    return;
                }
            }
        }
        v[idx] = true;
        power_b(idx+1, sum+1);
        v[idx] = false;
        power_b(idx+1, sum);
    }
    public static void power_w(int idx, int sum){
        if(idx == list_w.size()){ //마지막까지 오면
            if(result_w < sum){
                result_w = Math.max(result_w, sum); //결과값 갱신
            }
            return;
        }
        for (int i = 0; i < idx; i++) {
            if(v[i]){
                Pos prev = list_w.get(i);
                if(Math.abs(prev.r - list_w.get(idx).r) == Math.abs(prev.c - list_w.get(idx).c)){ //다음에 선택할 것과 이전에 선택한 것이 겹치면
                    v[idx] = false; //선택안하고
                    power_w(idx+1, sum); //다음으로
                    return;
                }
            }
        }
        v[idx] = true;
        power_w(idx+1, sum+1);
        v[idx] = false;
        power_w(idx+1, sum);
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        list_b = new ArrayList<>();
        list_w = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    if((i%2==0 && j%2==1) || i%2==1&&j%2==0){ //흑
                        list_b.add(new Pos(i,j));
                    }else{ //백
                        list_w.add(new Pos(i,j));
                    }
                }
            }
        }
        v = new boolean[list_b.size()];
        result_b = Integer.MIN_VALUE;
        power_b(0,0);

        v = new boolean[list_w.size()];
        result_w = Integer.MIN_VALUE;
        power_w(0,0);
        System.out.println(result_b+result_w);
    }

    public static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

//3시간 -> 질문글 보고 힌트 얻었다. 흑백으로 나누기