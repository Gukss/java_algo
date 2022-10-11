package _20221006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj_9205_beer {
    static int T,N;
    static List<Pos> list;
    static boolean[][] v;

    public static boolean start(){
        //시작점은 0, 도착점은 N+1
        for (int k = 0; k < N+2; k++) {
            for (int s = 0; s < N+2; s++) {
                for (int e = 0; e < N+2; e++) {
                    if(v[s][k] && v[k][e]){
                        v[s][e] = true;
                    }
                }
            }
        }
        return (v[0][N+1]);
    }

    public static void init(){
        for (int i = 0; i < list.size(); i++) {
            int r1 = list.get(i).r;
            int c1 = list.get(i).c;
            for (int j = 0; j < list.size(); j++) {
                int r2 = list.get(j).r;
                int c2 = list.get(j).c;
                int dist = Math.abs(r1-r2) + Math.abs(c1-c2);
                if(dist <= 1000){
                    v[i][j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T ; test_case++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            v = new boolean[N+2][N+2];
            for (int i = 0; i < N+2; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                list.add(new Pos(r,c));
            }
            init();
            if(start()){
                System.out.println("happy");
            }else{
                System.out.println("sad");
            }
        }
    }
    
    public static class Pos{
        int r,c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
/*
3
0
1000 1000
1000 1001
1
0 0
1000 0
0 2000
2
0 0
10000 0
0 1000
0 2000
ans:
happy
sad
happy
 */