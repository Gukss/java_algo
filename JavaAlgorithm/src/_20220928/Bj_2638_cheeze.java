package _20220928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_2638_cheeze {
    static int N,M;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0}; //위, 오, 밑, 왼
    static int[] dc = {0, 1, 0, -1};
    static int[][] store;
    static int result;
    static Queue<Pos> qu;
    static boolean[][] v;

    public static boolean done(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    public static void start(int r, int c){
        int count = 0;
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if(nr>=0&&nr<N&&nc>=0&&nc<M && map[nr][nc]==0 && v[nr][nc] == true){
                count += 1;
                if(count >=2){
                    store[r][c] = 1;
                    return;
                }
            }
        }

    }

    public static void bfs(){
        qu.add(new Pos(0,0));
        v[0][0] = true;
        while(!qu.isEmpty()){
            Pos cur = qu.poll();
            int sr = cur.r;
            int sc = cur.c;
            for (int i = 0; i < 4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];
                if(nr>=0&&nr<N&&nc>=0&&nc<M && !v[nr][nc]){
                    if(map[nr][nc] == 0){
                        v[nr][nc] = true;
                        qu.add(new Pos(nr,nc));
                    }else if(map[nr][nc] == 1){
                        start(nr,nc);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        store = new int[N][M];
        qu = new LinkedList<>();

        result = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(!done()){
            v = new boolean[N][M];
            bfs();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] -= store[i][j];
                }
            }
            store = new int[N][M];
            result += 1;
        }
        System.out.println(result);
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
9 9
0 0 0 0 0 0 0 0 0
0 1 1 1 1 1 1 1 0
0 1 0 0 0 0 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 1 0 1 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 0 0 0 0 1 0
0 1 1 1 1 1 1 1 0
0 0 0 0 0 0 0 0 0
ans: 3
 */