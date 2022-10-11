package _20221007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_eggegg {
    static int N;
    static Egg[] map;
    static int result;

    public static void recr(int idx){
        int temp = 0;
        for (int i = 0; i < N; i++) {
            if(map[i].s <= 0){
                temp += 1;
            }
        }
        result = Math.max(temp, result);
        if(idx==N){
            return;
        }
        for (int i = 0; i < N; i++) {
            if(map[idx].s <= 0){ //쥐고 있는 계란이 깨졌을 때
                recr(idx+1);
                return;
            }
            if(idx != i && map[i].s > 0){ //쥐고있는 계란과 같은 계란이 아닐 때, 칠 계란이 깨지지 않았을 때
                map[i].s -= map[idx].w;
                map[idx].s -= map[i].w;
                recr(idx+1);
                map[i].s += map[idx].w;
                map[idx].s += map[i].w;
            }
        }
    }

    public static void start(){
        boolean[] v = new boolean[N];
        recr(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new Egg[N];
        result = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[i] = new Egg(s,w);
        }
        start();
        System.out.println(result);
    }

    public static class Egg{
        int s,w;

        public Egg(int s, int w) {
            this.s = s;
            this.w = w;
        }
    }
}
